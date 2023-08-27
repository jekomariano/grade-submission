package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.CourseNotFoundException;
import com.ltp.gradesubmission.exception.DuplicateEnrollmentException;
import com.ltp.gradesubmission.exception.EntityNotFoundException;
import com.ltp.gradesubmission.repository.CourseRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final CommonService commonService;
    
    @Override
    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return (Course) commonService.unwrapObject(course, id, Course.class);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {  
        courseRepository.deleteById(id);      
    }

    @Override
    public List<Course> getCourses() {
        return (List<Course>)courseRepository.findAll();
    }

    @Override
    public Course addStudentToCourse(Long courseId, Long studentId) {
        Student student = studentService.getStudent(studentId);
        Course course = getCourse(courseId);
        boolean isExistingStudent = isEnrolledStudent(studentId, course);

        if (isExistingStudent) {
            throw new DuplicateEnrollmentException(student.getName(), courseId);
        }

        course.getStudents().add(student);
        return courseRepository.save(course);
    }

    @Override
    public List<Student> getEnrolledStudents(Long id) {
        Course course = getCourse(id);
        return course.getStudents();
    }


    private boolean isEnrolledStudent(long studentId, Course course) {
        return course.getStudents().stream().anyMatch(courseStudent -> courseStudent.getId().equals(studentId));
    }


    // COMMON SERVICE WILL HANDLE THIS
//    static Course unwrapCourse(Optional<Course> entity, Long id) {
//        if (entity.isPresent()) return entity.get();
//        else throw new EntityNotFoundException(id, Course.class);
//    }


}

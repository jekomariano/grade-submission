package com.ltp.gradesubmission.service;

import java.util.List;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.GradeNotFoundException;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.repository.StudentRepository;
import java.util.Optional;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {
    
    private final GradeRepository gradeRepository;
    private final StudentService studentService;
    private final CourseService courseService;
    private final CommonService commonService;

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        return (Grade) commonService.unwrapObject(grade, studentId, courseId, Grade.class);
    }

    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        Optional<Grade> existingStudent = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);


        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(courseId);



        grade.setStudent(student);
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        Grade unwrappedGrade = (Grade) commonService.unwrapObject(grade, studentId, courseId, Grade.class);
        unwrappedGrade.setScore(score);
        return gradeRepository.save(unwrappedGrade);
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {
        gradeRepository.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    @Override
    public List<Grade> getAllGrades() {
        return (List<Grade>) gradeRepository.findAll();
    }

    // COMMON SERVICE WILL HANDLE THIS
//    static Grade unwrapGrade(Optional<Grade> entity, Long studentId, Long courseId) {
//        if (entity.isPresent()) return entity.get();
//        else throw new GradeNotFoundException(studentId, courseId);
//    }


}

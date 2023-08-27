package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;

import com.ltp.gradesubmission.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.StudentNotFoundException;
import com.ltp.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {


    StudentRepository studentRepository;
    CommonService commonService;

    @Override
    public Student getStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return (Student) commonService.unwrapObject(student, id, Student.class);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {      
        studentRepository.deleteById(id);  
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>)studentRepository.findAll();
    }

    @Override
    public List<Course> getEnrolledCourses(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    // COMMON SERVICE WILL HANDLE THIS
//    static Student unwrapStudent(Optional<Student> entity, Long id) {
//        if (entity.isPresent()) return entity.get();
//        else throw new EntityNotFoundException(id, Student.class);
//    }

}
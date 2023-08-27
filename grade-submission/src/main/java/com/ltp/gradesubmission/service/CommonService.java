package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommonService {
    public Object unwrapObject(Optional<?> entity, Long id, Class<?> entityClass) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, entityClass);
    }

    public Object unwrapObject(Optional<?> entity, Long studentId, Long courseId, Class<?> entityClass) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(studentId, courseId, entityClass);
    }
}

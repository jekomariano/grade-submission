package com.ltp.gradesubmission.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + "with id '" + id + "' does not exist!");
    }

    public EntityNotFoundException(String entityName, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + "with username '" + entityName + "' does not exist!");
    }

    public EntityNotFoundException(long studentId, long courseId, Class<?> entity) {
        super("The "+ entity.getSimpleName().toLowerCase() +" with student id: '"
                + studentId + "' and course id: '" + courseId + "' does not exist in our records");
    }

}

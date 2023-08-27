package com.ltp.gradesubmission.exception;

public class DuplicateEnrollmentException extends RuntimeException {

    public DuplicateEnrollmentException(String student, long id) {
        super(student + " is already enrolled for course id: " + id);
    }

}

package com.ragul.StudentService.exception;

public class StudentNotActiveException extends RuntimeException {
    public StudentNotActiveException(String message) {
        super(message);
    }
}

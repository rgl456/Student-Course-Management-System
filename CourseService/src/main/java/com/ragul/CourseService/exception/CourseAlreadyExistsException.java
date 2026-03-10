package com.ragul.CourseService.exception;

public class CourseAlreadyExistsException extends RuntimeException {
    public CourseAlreadyExistsException(String message) {
        super(message);
    }
}

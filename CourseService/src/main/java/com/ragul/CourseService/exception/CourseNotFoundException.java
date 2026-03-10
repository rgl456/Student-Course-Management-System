package com.ragul.CourseService.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}

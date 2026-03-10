package com.ragul.StudentService.exception;

public class AlreadyEnrolledException extends RuntimeException {
  public AlreadyEnrolledException(String message) {
    super(message);
  }
}

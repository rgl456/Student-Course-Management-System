package com.ragul.StudentService.mapper;

import com.ragul.StudentService.dto.StudentResponse;
import com.ragul.StudentService.model.Student;

public class StudentMapper {

    public static StudentResponse mapToResponse(Student student){
        StudentResponse response = StudentResponse.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .dateOfBirth(student.getDateOfBirth())
                .status(student.getStatus())
                .enrolledCourseIds(student.getEnrolledCourseIds())
                .createdAt(student.getCreatedAt())
                .build();
        return response;
    }
}

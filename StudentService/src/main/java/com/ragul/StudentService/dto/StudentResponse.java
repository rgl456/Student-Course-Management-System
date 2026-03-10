package com.ragul.StudentService.dto;

import com.ragul.StudentService.model.StudentStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private StudentStatus status;
    private List<Long> enrolledCourseIds;
    private List<CourseResponse> enrolledCourses;
    private LocalDateTime createdAt;

}

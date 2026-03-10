package com.ragul.StudentService.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {
    private Long id;
    private String courseCode;
    private String title;
    private String instructor;
    private Integer availableSpots;
    private String status;
}
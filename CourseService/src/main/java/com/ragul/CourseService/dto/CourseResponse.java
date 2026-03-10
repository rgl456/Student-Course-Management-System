package com.ragul.CourseService.dto;

import com.ragul.CourseService.model.CourseStatus;

import java.time.LocalDateTime;

public record CourseResponse(
        Long id,
        String courseCode,
        String title,
        String description,
        String instructor,
        Integer maxCapacity,
        Integer currentEnrollment,
        Integer availableSpots,
        CourseStatus status,
        LocalDateTime createdAt
) {
}

package com.ragul.CourseService.dto;

import jakarta.validation.constraints.*;

public record CourseRequest(
        @NotBlank(message = "Course code is required")
        @Size(min = 2, max = 20, message = "Course code must be 2-20 characters")
        String courseCode,

        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 100, message = "Title must be 3-100 characters")
        String title,

        @Size(max = 1000, message = "Description cannot exceed 1000 characters")
        String description,

        @NotBlank(message = "Instructor is required")
        String instructor,

        @NotNull(message = "Max capacity is required")
        @Min(value = 1, message = "Max capacity must be at least 1")
        @Max(value = 500, message = "Max capacity cannot exceed 500")
        Integer maxCapacity
) {
}

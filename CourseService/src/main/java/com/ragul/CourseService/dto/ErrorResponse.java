package com.ragul.CourseService.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String message,
        LocalDateTime timeStamp
) {
}

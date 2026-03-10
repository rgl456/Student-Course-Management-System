package com.ragul.CourseService.mapper;

import com.ragul.CourseService.dto.CourseResponse;
import com.ragul.CourseService.model.Course;

public class CourseMapper {

    public static CourseResponse mapToResponse(Course course){
        return new CourseResponse(
                course.getId(),
                course.getCourseCode(),
                course.getTitle(),
                course.getDescription(),
                course.getInstructor(),
                course.getMaxCapacity(),
                course.getCurrentEnrollment(),
                course.getMaxCapacity() - course.getCurrentEnrollment(),
                course.getStatus(),
                course.getCreatedAt()
        );
    }

}

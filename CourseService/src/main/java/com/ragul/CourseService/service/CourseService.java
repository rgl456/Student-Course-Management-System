package com.ragul.CourseService.service;

import com.ragul.CourseService.dto.CourseRequest;
import com.ragul.CourseService.dto.CourseResponse;
import com.ragul.CourseService.model.Course;
import com.ragul.CourseService.model.CourseStatus;
import com.ragul.CourseService.repository.CourseRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;

    @Transactional
    public CourseResponse createCourse(@Valid CourseRequest courseRequest) {

        if(courseRepository.existsByCourseCode(courseRequest.courseCode())){
            throw new CourseAlreadyExistsException(
                    "Course with code " + courseRequest.courseCode() + " already exists"
            );
        }

        Course course = Course.builder()
                .courseCode(courseRequest.courseCode())
                .title(courseRequest.title())
                .description(courseRequest.description())
                .instructor(courseRequest.instructor())
                .maxCapacity(courseRequest.maxCapacity())
                .currentEnrollment(0)
                .status(CourseStatus.ACTIVE)
                .build();

        Course savedCourse = courseRepository.save(course);

        return new CourseResponse(
                savedCourse.getId(),
                savedCourse.getCourseCode(),
                savedCourse.getTitle(),
                savedCourse.getDescription(),
                savedCourse.getInstructor(),
                savedCourse.getMaxCapacity(),
                savedCourse.getCurrentEnrollment(),
                course.getMaxCapacity() - course.getCurrentEnrollment(),
                savedCourse.getStatus(),
                course.getCreatedAt()
        );
    }


}

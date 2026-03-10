package com.ragul.CourseService.service;

import com.ragul.CourseService.dto.CourseRequest;
import com.ragul.CourseService.dto.CourseResponse;
import com.ragul.CourseService.mapper.CourseMapper;
import com.ragul.CourseService.model.Course;
import com.ragul.CourseService.model.CourseStatus;
import com.ragul.CourseService.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;

    @Transactional
    public CourseResponse createCourse(CourseRequest courseRequest) {

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

        return CourseMapper.mapToResponse(savedCourse);
    }

    @Transactional(readOnly = true)
    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
        return CourseMapper.mapToResponse(course);
    }

    @Transactional(readOnly = true)
    public CourseResponse getCourseByCode(String courseCode) {
        Course course = courseRepository.findByCourseCode(courseCode)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with code: " + courseCode));
        return CourseMapper.mapToResponse(course);
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(CourseMapper::mapToResponse)
                .toList();
    }


    public List<CourseResponse> getActiveCourses() {

    }
}

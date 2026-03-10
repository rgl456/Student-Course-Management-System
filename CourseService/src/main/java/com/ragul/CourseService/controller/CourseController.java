package com.ragul.CourseService.controller;

import com.ragul.CourseService.dto.CourseRequest;
import com.ragul.CourseService.dto.CourseResponse;
import com.ragul.CourseService.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CourseRequest courseRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.createCourse(courseRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/code/{courseCode}")
    public ResponseEntity<CourseResponse> getCourseByCode(@PathVariable String courseCode) {
        return ResponseEntity.ok(courseService.getCourseByCode(courseCode));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/active")
    public ResponseEntity<List<CourseResponse>> getActiveCourses() {
        return ResponseEntity.ok(courseService.getActiveCourses());
    }

    @PatchMapping("/{id}/enroll")
    public ResponseEntity<CourseResponse> incrementEnrollment(@PathVariable("id") Long courseId) {
        return ResponseEntity.ok(courseService.incrementEnrollment(courseId));
    }

}

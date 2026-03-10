package com.ragul.StudentService.controller;

import com.ragul.StudentService.dto.StudentRequest;
import com.ragul.StudentService.dto.StudentResponse;
import com.ragul.StudentService.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest studentRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.createStudent(studentRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<StudentResponse> getStudentWithCourses(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentWithCourses(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<StudentResponse> enrollInCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.enrollInCourse(studentId, courseId));
    }

}

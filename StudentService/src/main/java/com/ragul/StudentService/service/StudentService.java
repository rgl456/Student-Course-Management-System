package com.ragul.StudentService.service;

import com.ragul.StudentService.client.CourseClient;
import com.ragul.StudentService.dto.CourseResponse;
import com.ragul.StudentService.dto.StudentRequest;
import com.ragul.StudentService.dto.StudentResponse;
import com.ragul.StudentService.exception.*;
import com.ragul.StudentService.mapper.StudentMapper;
import com.ragul.StudentService.model.Student;
import com.ragul.StudentService.model.StudentStatus;
import com.ragul.StudentService.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ragul.StudentService.mapper.StudentMapper.mapToResponse;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseClient courseClient;

    @Transactional
    public StudentResponse createStudent(StudentRequest studentRequest) {
        if (studentRepository.existsByEmail(studentRequest.email())) {
            throw new StudentAlreadyExistsException(
                    "Student with email " + studentRequest.email() + " already exists"
            );
        }

        Student student = Student.builder()
                .firstName(studentRequest.firstName())
                .lastName(studentRequest.lastName())
                .email(studentRequest.email())
                .dateOfBirth(studentRequest.dateOfBirth())
                .status(StudentStatus.ACTIVE)
                .build();

        Student savedStudent = studentRepository.save(student);

        return mapToResponse(savedStudent);
    }

    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        return mapToResponse(student);
    }

    @Transactional(readOnly = true)
    public StudentResponse getStudentWithCourses(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        StudentResponse response = mapToResponse(student);
        List<CourseResponse> courses = student.getEnrolledCourseIds()
                .stream()
                .map(courseId -> courseClient.getCourseById(courseId).orElse(null))
                .filter(course -> course != null)
                .toList();

        response.setEnrolledCourses(courses);
        return response;
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::mapToResponse)
                .toList();
    }

    @Transactional
    public StudentResponse enrollInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + studentId
                ));

        if (student.getStatus() != StudentStatus.ACTIVE) {
            throw new StudentNotActiveException("Only active students can enroll in courses");
        }

        if (student.getEnrolledCourseIds().contains(courseId)) {
            throw new AlreadyEnrolledException(
                    "Student is already enrolled in course: " + courseId
            );
        }

        courseClient.enrollInCourse(courseId)
                .orElseThrow(() -> new CourseNotFoundException(
                        "Course not found with id: " + courseId
                ));

        student.getEnrolledCourseIds().add(courseId);
        Student saved = studentRepository.save(student);

        return mapToResponse(saved);
    }
}

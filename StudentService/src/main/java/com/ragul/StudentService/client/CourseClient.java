package com.ragul.StudentService.client;

import com.ragul.StudentService.dto.CourseResponse;
import com.ragul.StudentService.exception.CourseEnrollmentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CourseClient {

    private final RestTemplate restTemplate;

    @Value("${course-service.url}")
    private String courseServiceUrl;

    public Optional<CourseResponse> getCourseById(Long courseId){
        try{
            String url = courseServiceUrl + "/api/v1/courses/" + courseId;

            CourseResponse response = restTemplate.getForObject(url, CourseResponse.class);
            return Optional.ofNullable(response);
        }
        catch(HttpClientErrorException.NotFound ex){
            log.error("http client error {}", ex.getMessage());
            return Optional.empty();
        }
        catch(ResourceAccessException ex){
            log.error("Course Service is unavailable: {}", ex.getMessage());
            return Optional.empty();
        }
    }

    public Optional<CourseResponse> enrollInCourse(Long courseId) {
        try{
            String url = courseServiceUrl + "/api/v1/courses/" + courseId + "/enroll";
            CourseResponse response = restTemplate.patchForObject(url, null, CourseResponse.class);
            return Optional.ofNullable(response);
        }
        catch (HttpClientErrorException.NotFound e) {
            log.warn("Course not found with id: {}", courseId);
            return Optional.empty();

        }
        catch (HttpClientErrorException.BadRequest e) {
            log.warn("Course is full, id: {}", courseId);
            throw new CourseEnrollmentException("Course is full");

        }
        catch (ResourceAccessException e) {
            log.error("Course Service is unavailable: {}", e.getMessage());
            throw new CourseEnrollmentException("Course Service is currently unavailable");
        }
    }

}

package com.ragul.CourseService.repository;

import com.ragul.CourseService.model.Course;
import com.ragul.CourseService.model.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByCourseCode(String courseCode);
    Optional<Course> findByCourseCode(String courseCode);
    List<Course> findAllByStatus(CourseStatus courseStatus);

}

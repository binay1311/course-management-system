package com.coursemanagementsystem.coursemanagementsystem.repository;

import com.coursemanagementsystem.coursemanagementsystem.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> getCourseById(Long courseId);

    Optional<Course> getCourseByTitle(String title);
}


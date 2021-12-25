package com.coursemanagementsystem.coursemanagementsystem.services;

import com.coursemanagementsystem.coursemanagementsystem.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    public List<Course> getCourses();

    public Optional<Course> getCourse(Long courseId) throws IllegalAccessException;

    public Integer addCourse(Course course) throws IllegalAccessException;

    public Course updateCourse(Course course);

    public Boolean deleteCourse(Long courseId) throws IllegalAccessException;

}

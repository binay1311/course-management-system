package com.coursemanagementsystem.coursemanagementsystem.services;

import com.coursemanagementsystem.coursemanagementsystem.entities.Course;
import com.coursemanagementsystem.coursemanagementsystem.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseServiceImpl() {
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourse(Long courseId) throws IllegalAccessException {
        Optional<Course> course = courseRepository.getCourseById(courseId);
        if(course.isEmpty()){
            throw new IllegalAccessException("course with courseId " + courseId + " does not exists");
        }
        return courseRepository.getCourseById(courseId);
    }

    @Override
    public Integer addCourse(Course course) throws IllegalAccessException {
        Optional<Course> getCourseByTitle = courseRepository.getCourseByTitle(course.getTitle());
        Optional<Course> getCouseById = courseRepository.getCourseById(course.getId());
        if(getCourseByTitle.isPresent() || getCouseById.isPresent()) {
            throw new IllegalAccessException("A course already exists with same Id or Title");
        }
        if ((int)course.getId() <= 0 &&
                course.getTitle() == null &&
                course.getDescription() == null) {
            throw new NullPointerException("A course with empty parameters cant be saved");
        }
        courseRepository.save(course);
        log.info("course " + course.getTitle() + " added successfully");
        return (int) course.getId();
    }

    @Override
    public Course updateCourse(Course course) {
        Long courseId = course.getId();
        String title = course.getTitle();
        String description = course.getDescription();

        Optional<Course> getCourse = courseRepository.findById(courseId);

        if(title != null && title.length()>0 && !getCourse.get().getTitle().equals(title)){
            Optional<Course> getCourseByTiTle = courseRepository.getCourseByTitle(title);
            if(getCourseByTiTle.isPresent()){
                throw new IllegalStateException
                        ("A course already exists with the same title " + title);
            }
            course.setTitle(title);
        }

        if(description != null && description.length()>0 &&
                !getCourse.get().getDescription().equals(description)){
            course.setDescription(description);
        }
        log.info("Successfully updated the course with the passed params");
        courseRepository.save(course);
        return course;
    }

    @Override
    public Boolean deleteCourse(Long courseId) throws IllegalAccessException {
        boolean exists = courseRepository.existsById(courseId);
        if(!exists){
            throw new IllegalAccessException(
                    "course with courseId " + courseId + " does not exists");
        }
        courseRepository.deleteById(courseId);
        log.info("course with courseId " + courseId + " is deleted successfully");
        return true;
    }
}

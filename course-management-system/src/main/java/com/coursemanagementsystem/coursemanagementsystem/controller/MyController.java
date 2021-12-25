package com.coursemanagementsystem.coursemanagementsystem.controller;

import com.coursemanagementsystem.coursemanagementsystem.constants.StatusCode;
import com.coursemanagementsystem.coursemanagementsystem.entities.Course;
import com.coursemanagementsystem.coursemanagementsystem.models.Response;
import com.coursemanagementsystem.coursemanagementsystem.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(path = "/courses")
public class MyController {

    private final CourseService courseService;

    @Autowired
    public MyController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public Response<?> getCourses(){
        log.info("Fetching all courses");
        return Response.<List<Course>>builder()
                .response(courseService.getCourses())
                .responseCode(StatusCode.SUCCESS.getCode())
                .responseMessage(StatusCode.SUCCESS.getMessage())
                .build();
    }

    @GetMapping("/{courseId}")
    public Response<?> getCourse(@PathVariable String courseId) throws IllegalAccessException {
        log.info("Fetching course with courseId " + courseId);
        return Response.<Optional<Course>>builder()
                .response(courseService.getCourse(Long.parseLong(courseId)))
                .responseCode(StatusCode.SUCCESS.getCode())
                .responseMessage(StatusCode.SUCCESS.getMessage())
                .build();
    }

    @PostMapping
    public Response<?> addCourse(@RequestBody Course course) throws IllegalAccessException {
        log.info("API for adding course called");
        return Response.<Integer>builder()
                .response(courseService.addCourse(course))
                .responseCode(StatusCode.SUCCESS.getCode())
                .responseMessage(StatusCode.SUCCESS.getMessage())
                .build();
    }

    @PutMapping("/{courseId}")
    public Response<?> updateCourse(@RequestBody Course course) throws IllegalStateException{

        log.info("API for updating course called");
        return Response.<Course>builder()
                .response(courseService.updateCourse(course))
                .responseCode(StatusCode.SUCCESS.getCode())
                .responseMessage(StatusCode.SUCCESS.getMessage())
                .build();
    }

    @DeleteMapping("/{courseId}")
    public Response<?> deleteCourse(@PathVariable String courseId) throws IllegalAccessException {
        log.info("API for deleting course called");
        if(courseService.deleteCourse(Long.parseLong(courseId))){
            return Response.<Void>builder()
                    .responseCode(StatusCode.SUCCESS.getCode())
                    .responseMessage(StatusCode.SUCCESS.getMessage())
                    .build();
        }
        return Response.<Void>builder()
                .responseCode(StatusCode.FAILED.getCode())
                .responseMessage(StatusCode.FAILED.getMessage())
                .build();
    }
}

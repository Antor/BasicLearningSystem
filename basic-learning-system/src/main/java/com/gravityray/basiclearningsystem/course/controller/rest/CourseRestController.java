package com.gravityray.basiclearningsystem.course.controller.rest;

import com.gravityray.basiclearningsystem.course.converter.CourseConverter;
import com.gravityray.basiclearningsystem.course.model.*;
import com.gravityray.basiclearningsystem.course.service.CourseService;
import com.gravityray.basiclearningsystem.unit.UnitConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CourseRestController {

    private final CourseService courseService;
    private final CourseConverter courseConverter;
    private final UnitConverter unitConverter;

    public CourseRestController(
            CourseService courseService,
            CourseConverter courseConverter,
            UnitConverter unitConverter) {
        this.courseService = courseService;
        this.courseConverter = courseConverter;
        this.unitConverter = unitConverter;
    }

    @GetMapping("/course")
    public List<CourseDto> getAllCourses(
            @RequestParam(name = "onlyActive", defaultValue = "false") boolean onlyActive) {
        return courseService.getCourses(onlyActive)
                .stream()
                .map(courseConverter::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/course/{courseId}")
    public CourseDto getCourse(@PathVariable long courseId) {
        CourseEntity courseEntity = courseService.getCourse(courseId);
        CourseDto courseDto = courseConverter.toDto(courseEntity);
        courseDto.setUnits(
                courseService.getCourseUnits(courseId)
                        .stream()
                        .map(unitConverter::toDto)
                        .collect(Collectors.toList()));
        return courseDto;
    }

    @PostMapping("/activate-course")
    public void activateCourse(@RequestBody long courseId) {
        courseService.activateCourse(courseId);
    }

    @PostMapping("/deactivate-course")
    public void deactivateCourse(@RequestBody long courseId) {
        courseService.deactivateCourse(courseId);
    }

    @PostMapping("/course")
    public CourseDto createCourse(@RequestBody CourseDto course) {
        long courseId = courseService.addCourse(courseConverter.toEntity(course));
        course.setId(courseId);
        return course;
    }

    @PutMapping("/course")
    public void updateCourse(@RequestBody CourseDto course) {
        courseService.updateCourse(courseConverter.toEntity(course));
    }

    @DeleteMapping("/course/{courseId}")
    public void deleteCourse(@PathVariable long courseId) {
        courseService.deleteCourse(courseId);
    }
}

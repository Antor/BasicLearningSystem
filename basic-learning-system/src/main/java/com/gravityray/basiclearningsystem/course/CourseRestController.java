package com.gravityray.basiclearningsystem.course;

import com.gravityray.basiclearningsystem.course.model.*;
import com.gravityray.basiclearningsystem.course.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CourseRestController {

    private final CourseService courseService;
    private final CourseConverter courseConverter;

    public CourseRestController(
            CourseService courseService,
            CourseConverter courseConverter) {
        this.courseService = courseService;
        this.courseConverter = courseConverter;
    }

    @GetMapping("/course-all")
    public List<CourseDto> getAllCourses() {
        return courseService.getAllCourses()
                .stream()
                .map(courseConverter::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/course-active")
    public List<CourseDto> getActiveCourses() {
        return courseService.getActiveCourses()
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
                        .map(courseConverter::toDto)
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

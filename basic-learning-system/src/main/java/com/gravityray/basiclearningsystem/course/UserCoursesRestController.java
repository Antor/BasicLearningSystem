package com.gravityray.basiclearningsystem.course;

import com.gravityray.basiclearningsystem.course.model.CourseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UserCoursesRestController {

    private final CourseService courseService;
    private final CourseConverter courseConverter;

    public UserCoursesRestController(
            CourseService courseService,
            CourseConverter courseConverter) {
        this.courseService = courseService;
        this.courseConverter = courseConverter;
    }

    @GetMapping("/me/course")
    public List<CourseDto> getUserCourses() {
        long userId = -1; // TODO: get from Spring Security
        return courseService.getUserCourses(userId)
                .stream()
                .map(courseConverter::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/me/enroll")
    public void enrollUserToCourse(@RequestBody Long courseId) {
        long userId = -1; // TODO: get from Spring Security
        courseService.enrollUserToCourse(userId, courseId);
    }

    @PostMapping("/me/unenroll")
    public void unenrollUserToCourse(@RequestBody Long courseId) {
        long userId = -1; // TODO: get from Spring Security
        courseService.unenrollUserFromCourse(userId, courseId);
    }
}

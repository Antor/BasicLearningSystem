package com.gravityray.basiclearningsystem.course.controller;

import com.gravityray.basiclearningsystem.course.service.CourseConverter;
import com.gravityray.basiclearningsystem.course.model.CourseDto;
import com.gravityray.basiclearningsystem.course.service.CourseService;
import com.gravityray.basiclearningsystem.user.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UserCoursesRestController {

    private final UserService userService;
    private final CourseService courseService;
    private final CourseConverter courseConverter;

    public UserCoursesRestController(
            UserService userService,
            CourseService courseService,
            CourseConverter courseConverter) {
        this.userService = userService;
        this.courseService = courseService;
        this.courseConverter = courseConverter;
    }

    @GetMapping("/me/course")
    public List<CourseDto> getUserCourses(@AuthenticationPrincipal UserDetails userDetails) {
        long userId = userService.getUser(userDetails.getUsername()).getId();
        return courseService.getUserCourses(userId)
                .stream()
                .map(courseConverter::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/me/enroll")
    public void enrollUserToCourse(
            @RequestBody Long courseId,
            @AuthenticationPrincipal UserDetails userDetails) {
        long userId = userService.getUser(userDetails.getUsername()).getId();
        courseService.enrollUserToCourse(userId, courseId);
    }

    @SuppressWarnings("SpellCheckingInspection")
    @PostMapping("/me/unenroll")
    public void unenrollUserToCourse(
            @RequestBody Long courseId,
            @AuthenticationPrincipal UserDetails userDetails) {
        long userId = userService.getUser(userDetails.getUsername()).getId();
        courseService.unenrollUserFromCourse(userId, courseId);
    }
}

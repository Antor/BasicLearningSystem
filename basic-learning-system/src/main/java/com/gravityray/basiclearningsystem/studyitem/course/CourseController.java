package com.gravityray.basiclearningsystem.studyitem.course;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String courseList(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails != null ? userDetails.getUsername() : null;
        model.addAttribute(
                "info",
                courseService.getActiveCourseItemListInfo(email));

        return "course_list";
    }

    @GetMapping("/{courseId}/enrol")
    public String courseEnrolGet(@PathVariable long courseId) {
        // TODO
        return "course_enrol";
    }

    @PostMapping("/{courseId}/enrol")
    public String courseEnrolPost(@PathVariable long courseId) {
        // TODO
        throw new RuntimeException("NOT IMPLEMENTED");
    }

    @GetMapping("/{courseId}/leave")
    public String courseLeaveGet(@PathVariable long courseId) {
        // TODO
        return "course_leave";
    }

    @PostMapping("/{courseId}/leave")
    public String courseLeavePost(@PathVariable long courseId) {
        // TODO
        throw new RuntimeException("NOT IMPLEMENTED");
    }

}

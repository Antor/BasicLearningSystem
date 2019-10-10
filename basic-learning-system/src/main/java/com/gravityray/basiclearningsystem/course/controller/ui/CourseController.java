package com.gravityray.basiclearningsystem.course.controller.ui;

import com.gravityray.basiclearningsystem.course.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/course")
    public String courseList(Model model) {
        model.addAttribute("courses", courseService.getCourses(true));

        return "course_list";
    }

}

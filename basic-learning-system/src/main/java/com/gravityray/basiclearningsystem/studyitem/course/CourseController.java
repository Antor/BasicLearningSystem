package com.gravityray.basiclearningsystem.studyitem.course;

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

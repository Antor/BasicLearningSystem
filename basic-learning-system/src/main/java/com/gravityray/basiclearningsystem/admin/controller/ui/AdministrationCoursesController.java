package com.gravityray.basiclearningsystem.admin.controller.ui;

import com.gravityray.basiclearningsystem.course.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministrationCoursesController {

    private final CourseService courseService;

    public AdministrationCoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/admin/course")
    public String administrationCourses(Model model) {
        model.addAttribute("courses", courseService.getCourses(false));

        return "administration/administration_courses";
    }

    @GetMapping("/admin/course/create")
    public String courseCreate() {
        return "administration/course_create";
    }
}

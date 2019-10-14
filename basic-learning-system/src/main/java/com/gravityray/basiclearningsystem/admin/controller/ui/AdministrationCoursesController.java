package com.gravityray.basiclearningsystem.admin.controller.ui;

import com.gravityray.basiclearningsystem.course.service.CourseNotFoundException;
import com.gravityray.basiclearningsystem.course.service.CourseService;
import com.gravityray.basiclearningsystem.course.service.CreateCourseFormNotValidException;
import com.gravityray.basiclearningsystem.course.service.EditCourseFormNotValidException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

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
    public String courseCreateGet(Model model) {
        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", new CreateCourseForm());

        return "administration/course_create";
    }

    @PostMapping("/admin/course/create")
    public String courseCreatePost(Model model, CreateCourseForm courseForm) {
        try {
            courseService.createCourse(courseForm);
            return "redirect:/admin/course";

        } catch (CreateCourseFormNotValidException e) {
            model.addAttribute("errors", e.getErrorList());
            model.addAttribute("course", courseForm);
            return "administration/course_create";
        }
    }

    @GetMapping("/admin/course/{courseId}")
    public String courseEditGet(
            @PathVariable Long courseId,
            Model model) {

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", courseService.getEditCourseForm(courseId));

        return "administration/course_edit";
    }

    @PostMapping("/admin/course/{courseId}")
    public String courseEditPost(
            Model model,
            EditCourseForm editCourseForm) throws CourseNotFoundException {

        try {
            courseService.updateCourse(editCourseForm);
            return "redirect:/admin/course";

        } catch (EditCourseFormNotValidException e) {
            model.addAttribute("errors", e.getErrorList());
            model.addAttribute("course", editCourseForm);
            return "administration/course_edit";
        }


    }
}

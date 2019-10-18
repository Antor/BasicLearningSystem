package com.gravityray.basiclearningsystem.admin.controller;

import com.gravityray.basiclearningsystem.admin.model.CreateCourseForm;
import com.gravityray.basiclearningsystem.admin.model.CreateUnitForm;
import com.gravityray.basiclearningsystem.admin.model.EditCourseForm;
import com.gravityray.basiclearningsystem.course.model.CourseEntity;
import com.gravityray.basiclearningsystem.course.service.CourseNotFoundException;
import com.gravityray.basiclearningsystem.course.service.CourseService;
import com.gravityray.basiclearningsystem.course.service.CreateCourseFormNotValidException;
import com.gravityray.basiclearningsystem.course.service.EditCourseFormNotValidException;
import com.gravityray.basiclearningsystem.unit.service.CreateUnitFormNotValidException;
import com.gravityray.basiclearningsystem.unit.service.UnitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/admin/course")
public class AdministrationCourseController {

    private final CourseService courseService;
    private final UnitService unitService;

    public AdministrationCourseController(
            CourseService courseService,
            UnitService unitService) {
        this.courseService = courseService;
        this.unitService = unitService;
    }

    @GetMapping
    public String courseList(Model model) {
        model.addAttribute("courses", courseService.getCourses(false));

        return "administration/course/list";
    }

    @GetMapping("/create")
    public String courseCreateGet(Model model) {
        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", new CreateCourseForm());

        return "administration/course/create";
    }

    @PostMapping("/create")
    public String courseCreatePost(Model model, CreateCourseForm courseForm) {
        try {
            courseService.createCourse(courseForm);
            return "redirect:/admin/course";

        } catch (CreateCourseFormNotValidException e) {
            model.addAttribute("errors", e.getErrorList());
            model.addAttribute("course", courseForm);
            return "administration/course/create";
        }
    }

    @GetMapping("/{courseId}")
    public String courseEditGet(
            @PathVariable Long courseId,
            Model model) {

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", courseService.getEditCourseForm(courseId));

        return "administration/course/edit";
    }

    @PostMapping("/{courseId}")
    public String courseEditPost(
            @SuppressWarnings("unused") @PathVariable Long courseId,
            Model model,
            EditCourseForm editCourseForm) throws CourseNotFoundException {

        try {
            courseService.updateCourse(editCourseForm);
            return "redirect:/admin/course";

        } catch (EditCourseFormNotValidException e) {
            model.addAttribute("errors", e.getErrorList());
            model.addAttribute("course", editCourseForm);
            return "administration/course/edit";
        }
    }

    @GetMapping("/{courseId}/delete")
    public String courseDeleteGet(
            @PathVariable Long courseId,
            Model model) {

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", courseService.getEditCourseForm(courseId));

        return "administration/course/delete";
    }

    @PostMapping("/{courseId}/delete")
    public String courseDeletePost(@PathVariable Long courseId) {

        courseService.deleteCourse(courseId);

        return "redirect:/admin/course";
    }

    @GetMapping("/{courseId}/activate")
    public String courseActivateGet(
            @PathVariable Long courseId,
            Model model) {

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", courseService.getCourseActiveToggleInfo(courseId));

        return "administration/course/active_toggle";
    }

    @GetMapping("/{courseId}/deactivate")
    public String courseDeactivateGet(
            @PathVariable Long courseId,
            Model model) {

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", courseService.getCourseActiveToggleInfo(courseId));

        return "administration/course/active_toggle";
    }

    @PostMapping("/{courseId}/active/toggle")
    public String courseToggleActive(@PathVariable Long courseId) {

        courseService.toggleCourseActive(courseId);

        return "redirect:/admin/course";
    }

    @GetMapping("/{courseId}/unit")
    public String unitList(
            @PathVariable Long courseId,
            Model model) {

        CourseEntity course = courseService.getCourse(courseId);
        model.addAttribute("course", course);
        model.addAttribute("units", course.getUnitList());

        return "administration/unit/list";
    }

    @GetMapping("/{courseId}/unit/create")
    public String unitCreateGet(
            @PathVariable Long courseId,
            Model model) {
        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", courseService.getCourse(courseId));
        model.addAttribute("unit", new CreateUnitForm());

        return "administration/unit/create";
    }

    @PostMapping("/{courseId}/unit/create")
    public String unitCreatePost(
            @PathVariable Long courseId,
            Model model,
            CreateUnitForm createUnitForm) {

        try {
            unitService.createUnit(courseId, createUnitForm);
            return String.format("redirect:/admin/course/%d/unit", courseId);

        } catch (CreateUnitFormNotValidException e) {
            model.addAttribute("errors", e.getErrorList());
            model.addAttribute("course", courseService.getCourse(courseId));
            model.addAttribute("unit", createUnitForm);
            return "administration/unit/create";
        }
    }
}

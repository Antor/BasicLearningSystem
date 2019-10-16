package com.gravityray.basiclearningsystem.admin.controller;

import com.gravityray.basiclearningsystem.admin.model.CreateUnitForm;
import com.gravityray.basiclearningsystem.course.service.CourseService;
import com.gravityray.basiclearningsystem.unit.service.CreateUnitFormNotValidException;
import com.gravityray.basiclearningsystem.unit.service.UnitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class AdministrationUnitController {

    private final CourseService courseService;
    private final UnitService unitService;

    public AdministrationUnitController(
            CourseService courseService,
            UnitService unitService) {
        this.courseService = courseService;
        this.unitService = unitService;
    }

    @GetMapping("/admin/course/{courseId}/unit/create")
    public String unitCreateGet(
            @PathVariable Long courseId,
            Model model) {
        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", courseService.getCourse(courseId));
        model.addAttribute("unit", new CreateUnitForm());

        return "administration/unit_create";
    }

    @PostMapping("/admin/course/{courseId}/unit/create")
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
            return "administration/unit_create";
        }
    }

    @GetMapping("/admin/course/{courseId}/unit/{unitId}")
    public String unitEditGet(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            Model model) {

        // TODO

        return "administration/unit_edit";
    }

    @GetMapping("/admin/course/{courseId}/unit/{unitId}/delete")
    public String unitDeleteGet(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            Model model) {

        // TODO

        return "administration/unit_delete";
    }

    @GetMapping("/admin/course/{courseId}/unit/{unitId}/lesson")
    public String unitList(
            @PathVariable Long courseId,
            @PathVariable Long unitId) {

        // TODO

        return "administration/lesson_list";
    }
}

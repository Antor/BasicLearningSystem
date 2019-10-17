package com.gravityray.basiclearningsystem.admin.controller;

import com.gravityray.basiclearningsystem.admin.model.CreateLessonForm;
import com.gravityray.basiclearningsystem.admin.model.EditLessonForm;
import com.gravityray.basiclearningsystem.course.service.EditUnitFormNotValidException;
import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.lesson.service.CreateLessonFormNotValidException;
import com.gravityray.basiclearningsystem.lesson.service.EditLessonFormNotValidException;
import com.gravityray.basiclearningsystem.lesson.service.LessonService;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;
import com.gravityray.basiclearningsystem.unit.service.UnitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class AdministrationLessonController {

    private final UnitService unitService;
    private final LessonService lessonService;

    public AdministrationLessonController(
            UnitService unitService,
            LessonService lessonService) {
        this.unitService = unitService;
        this.lessonService = lessonService;
    }

    @GetMapping("/admin/course/{courseId}/unit/{unitId}/lesson/create")
    public String lessonCreateGet(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            Model model) {

        UnitEntity unitEntity = unitService.getUnit(unitId);

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", unitEntity.getCourse());
        model.addAttribute("unit", unitEntity);
        model.addAttribute("lesson", new CreateLessonForm());

        return "administration/lesson_create";
    }

    @PostMapping("/admin/course/{courseId}/unit/{unitId}/lesson/create")
    public String lessonCreatePost(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            Model model,
            CreateLessonForm createLessonForm) {

        try {
            lessonService.createLesson(unitId, createLessonForm);
            return String.format("redirect:/admin/course/%d/unit/%d/lesson", courseId, unitId);

        } catch (CreateLessonFormNotValidException e) {
            model.addAttribute("errors", e.getErrorList());

            UnitEntity unitEntity = unitService.getUnit(unitId);
            model.addAttribute("course", unitEntity.getCourse());
            model.addAttribute("unit", unitEntity);

            model.addAttribute("lesson", createLessonForm);
            return "administration/lesson_create";
        }
    }

    @GetMapping("/admin/course/{courseId}/unit/{unitId}/lesson/{lessonId}")
    public String lessonEditGet(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            @PathVariable Long lessonId,
            Model model) {

        LessonEntity lessonEntity = lessonService.getLesson(lessonId);

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", lessonEntity.getUnit().getCourse());
        model.addAttribute("unit", lessonEntity.getUnit());
        model.addAttribute("lesson", lessonEntity);

        return "administration/lesson_edit";
    }

    @PostMapping("/admin/course/{courseId}/unit/{unitId}/lesson/{lessonId}")
    public String lessonEditPost(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            @PathVariable Long lessonId,
            Model model,
            EditLessonForm editLessonForm) {
        try {
            lessonService.updateLesson(editLessonForm);
            return String.format("redirect:/admin/course/%d/unit/%d/lesson", courseId, unitId);

        } catch (EditLessonFormNotValidException e) {
            model.addAttribute("errors", e.getErrorList());

            UnitEntity unitEntity = unitService.getUnit(unitId);
            model.addAttribute("course", unitEntity.getCourse());
            model.addAttribute("unit", unitEntity);

            model.addAttribute("lesson", editLessonForm);

            return "administration/lesson_edit";
        }
    }

    @GetMapping("/admin/course/{courseId}/unit/{unitId}/lesson/{lessonId}/delete")
    public String lessonDeleteGet(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            @PathVariable Long lessonId,
            Model model) {

        LessonEntity lessonEntity = lessonService.getLesson(lessonId);

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", lessonEntity.getUnit().getCourse());
        model.addAttribute("unit", lessonEntity.getUnit());
        model.addAttribute("lesson", lessonEntity);

        return "administration/lesson_delete";
    }

    @PostMapping("/admin/course/{courseId}/unit/{unitId}/lesson/{lessonId}/delete")
    public String lessonDeletePost(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            @PathVariable Long lessonId,
            Model model) {

        lessonService.deleteLesson(lessonId);

        return String.format("redirect:/admin/course/%d/unit/%d/lesson", courseId, unitId);
    }

    @GetMapping("/admin/course/{courseId}/unit/{unitId}/lesson/{lessonId}/item")
    public String lessonItemList(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            @PathVariable Long lessonId,
            Model model) {

        LessonEntity lessonEntity = lessonService.getLesson(lessonId);
        model.addAttribute("course", lessonEntity.getUnit().getCourse());
        model.addAttribute("unit", lessonEntity.getUnit());
        model.addAttribute("lesson", lessonEntity);
        model.addAttribute("lessonItems", lessonEntity.getLessonItems());

        return "administration/lesson_item_list";
    }
}

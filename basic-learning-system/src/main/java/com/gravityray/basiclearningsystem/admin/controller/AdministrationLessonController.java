package com.gravityray.basiclearningsystem.admin.controller;

import com.gravityray.basiclearningsystem.admin.model.CreateLessonItemForm;
import com.gravityray.basiclearningsystem.admin.model.EditLessonForm;
import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.lesson.service.EditLessonFormNotValidException;
import com.gravityray.basiclearningsystem.lesson.service.LessonService;
import com.gravityray.basiclearningsystem.lessonitem.service.CreateLessonItemFormNotValidException;
import com.gravityray.basiclearningsystem.lessonitem.service.LessonItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/admin/lesson")
public class AdministrationLessonController {

    private final LessonService lessonService;
    private final LessonItemService lessonItemService;

    public AdministrationLessonController(
            LessonService lessonService,
            LessonItemService lessonItemService) {
        this.lessonService = lessonService;
        this.lessonItemService = lessonItemService;
    }

    @GetMapping("/{lessonId}")
    public String lessonEditGet(@PathVariable long lessonId, Model model) {

        LessonEntity lessonEntity = lessonService.getLesson(lessonId);

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", lessonEntity.getUnit().getCourse());
        model.addAttribute("unit", lessonEntity.getUnit());
        model.addAttribute("lesson", lessonEntity);

        return "administration/lesson/edit";
    }

    @PostMapping("/{lessonId}")
    public String lessonEditPost(
            @PathVariable long lessonId,
            Model model,
            EditLessonForm editLessonForm) {
        LessonEntity lessonEntity = lessonService.getLesson(lessonId);
        try {
            lessonService.updateLesson(editLessonForm);
            return String.format("redirect:/admin/unit/%d/lesson", lessonEntity.getUnitId());

        } catch (EditLessonFormNotValidException e) {
            model.addAttribute("errors", e.getErrorList());

            model.addAttribute("course", lessonEntity.getUnit().getCourse());
            model.addAttribute("unit", lessonEntity.getUnit());

            model.addAttribute("lesson", editLessonForm);

            return "administration/lesson/edit";
        }
    }

    @GetMapping("/{lessonId}/delete")
    public String lessonDeleteGet(@PathVariable long lessonId, Model model) {

        LessonEntity lessonEntity = lessonService.getLesson(lessonId);

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", lessonEntity.getUnit().getCourse());
        model.addAttribute("unit", lessonEntity.getUnit());
        model.addAttribute("lesson", lessonEntity);

        return "administration/lesson/delete";
    }

    @PostMapping("/{lessonId}/delete")
    public String lessonDeletePost(@PathVariable long lessonId) {
        LessonEntity lessonEntity = lessonService.getLesson(lessonId);

        lessonService.deleteLesson(lessonId);

        return String.format("redirect:/admin/unit/%d/lesson", lessonEntity.getUnitId());
    }

    @GetMapping("/{lessonId}/item")
    public String lessonItemList(
            @PathVariable Long lessonId,
            Model model) {

        LessonEntity lessonEntity = lessonService.getLesson(lessonId);
        model.addAttribute("course", lessonEntity.getUnit().getCourse());
        model.addAttribute("unit", lessonEntity.getUnit());
        model.addAttribute("lesson", lessonEntity);
        model.addAttribute("lessonItems", lessonEntity.getLessonItems());

        return "administration/lesson_item/list";
    }

    @GetMapping("/{lessonId}/item/create")
    public String lessonItemCreateGet(
            @PathVariable Long lessonId,
            Model model) {
        LessonEntity lessonEntity = lessonService.getLesson(lessonId);

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", lessonEntity.getUnit().getCourse());
        model.addAttribute("unit", lessonEntity.getUnit());
        model.addAttribute("lesson", lessonEntity);
        model.addAttribute("lessonItem", new CreateLessonItemForm());

        return "administration/lesson_item/create";
    }

    @PostMapping("/{lessonId}/item/create")
    public String lessonItemCreatePost(
            @PathVariable Long lessonId,
            Model model,
            CreateLessonItemForm createLessonItemForm) {
        try {
            lessonItemService.createLessonItem(lessonId, createLessonItemForm);
            return String.format("redirect:/admin/lesson/%d/item", lessonId);

        } catch (CreateLessonItemFormNotValidException e) {
            model.addAttribute("errors", e.getErrorList());

            LessonEntity lessonEntity = lessonService.getLesson(lessonId);
            model.addAttribute("course", lessonEntity.getUnit().getCourse());
            model.addAttribute("unit", lessonEntity.getUnit());
            model.addAttribute("lesson", lessonEntity);

            model.addAttribute("lessonItem", createLessonItemForm);
            return "administration/lesson_item/create";
        }
    }
}

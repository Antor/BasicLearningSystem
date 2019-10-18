package com.gravityray.basiclearningsystem.admin.controller;

import com.gravityray.basiclearningsystem.admin.model.EditLessonItemForm;
import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.lesson.service.LessonService;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;
import com.gravityray.basiclearningsystem.lessonitem.service.EditLessonItemFormNotValidException;
import com.gravityray.basiclearningsystem.lessonitem.service.LessonItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/admin/lesson-item")
public class AdministrationLessonItemController {

    private final LessonService lessonService;
    private final LessonItemService lessonItemService;

    public AdministrationLessonItemController(
            LessonService lessonService,
            LessonItemService lessonItemService) {
        this.lessonService = lessonService;
        this.lessonItemService = lessonItemService;
    }

    @GetMapping("/{lessonItemId}")
    public String lessonItemEditGet(@PathVariable Long lessonItemId, Model model) {
        LessonItemEntity lessonItemEntity = lessonItemService.getLessonItem(lessonItemId);

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", lessonItemEntity.getLesson().getUnit().getCourse());
        model.addAttribute("unit", lessonItemEntity.getLesson().getUnit());
        model.addAttribute("lesson", lessonItemEntity.getLesson());
        model.addAttribute("lessonItem", lessonItemEntity);

        return "administration/lesson_item/edit";
    }

    @PostMapping("/{lessonItemId}")
    public String lessonItemEditPost(
            @PathVariable Long lessonItemId,
            Model model,
            EditLessonItemForm editLessonItemForm) {
        LessonItemEntity lessonItemEntity = lessonItemService.getLessonItem(lessonItemId);
        try {
            lessonItemService.updateLessonItem(editLessonItemForm);
            return String.format("redirect:/admin/lesson/%d/item", lessonItemEntity.getLessonId());

        } catch (EditLessonItemFormNotValidException e) {
            model.addAttribute("errors", e.getErrorList());

            LessonEntity lessonEntity = lessonService.getLesson(lessonItemEntity.getLessonId());
            model.addAttribute("course", lessonEntity.getUnit().getCourse());
            model.addAttribute("unit", lessonEntity.getUnit());
            model.addAttribute("lesson", lessonEntity);

            model.addAttribute("lessonItem", editLessonItemForm);

            return "administration/lesson_item/edit";
        }
    }

    @GetMapping("/{lessonItemId}/delete")
    public String lessonItemDeleteGet(@PathVariable Long lessonItemId, Model model) {
        LessonItemEntity lessonItemEntity = lessonItemService.getLessonItem(lessonItemId);

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", lessonItemEntity.getLesson().getUnit().getCourse());
        model.addAttribute("unit", lessonItemEntity.getLesson().getUnit());
        model.addAttribute("lesson", lessonItemEntity.getLesson());
        model.addAttribute("lessonItem", lessonItemEntity);

        return "administration/lesson_item/delete";
    }

    @PostMapping("/{lessonItemId}/delete")
    public String lessonItemDeletePost(@PathVariable Long lessonItemId) {
        long lessonId = lessonItemService.getLessonItem(lessonItemId).getLessonId();

        lessonItemService.deleteLessonItem(lessonItemId);

        return String.format("redirect:/admin/lesson/%d/item", lessonId);
    }
}

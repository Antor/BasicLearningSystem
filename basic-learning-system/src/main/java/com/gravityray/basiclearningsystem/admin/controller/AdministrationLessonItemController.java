package com.gravityray.basiclearningsystem.admin.controller;

import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;
import com.gravityray.basiclearningsystem.lessonitem.service.LessonItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class AdministrationLessonItemController {

    private final LessonItemService lessonItemService;

    public AdministrationLessonItemController(LessonItemService lessonItemService) {
        this.lessonItemService = lessonItemService;
    }

    @GetMapping("/admin/course/{courseId}/unit/{unitId}/lesson/{lessonId}/item/create")
    public String lessonItemCreateGet(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            @PathVariable Long lessonId,
            Model model) {
        // TODO
        return "administration/lesson_item_create";
    }

    @GetMapping("/admin/course/{courseId}/unit/{unitId}/lesson/{lessonId}/item/{lessonItemId}")
    public String lessonItemEditGet(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            @PathVariable Long lessonId,
            @PathVariable Long lessonItemId,
            Model model) {

        // TODO

        return "administration/lesson_item_edit";
    }

    @GetMapping("/admin/course/{courseId}/unit/{unitId}/lesson/{lessonId}/item/{lessonItemId}/delete")
    public String lessonItemDeleteGet(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            @PathVariable Long lessonId,
            @PathVariable Long lessonItemId,
            Model model) {

        LessonItemEntity lessonItemEntity = lessonItemService.getLessonItem(lessonItemId);

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", lessonItemEntity.getLesson().getUnit().getCourse());
        model.addAttribute("unit", lessonItemEntity.getLesson().getUnit());
        model.addAttribute("lesson", lessonItemEntity.getLesson());
        model.addAttribute("lessonItem", lessonItemEntity);

        return "administration/lesson_item_delete";
    }

    @PostMapping("/admin/course/{courseId}/unit/{unitId}/lesson/{lessonId}/item/{lessonItemId}/delete")
    public String lessonItemDeletePost(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            @PathVariable Long lessonId,
            @PathVariable Long lessonItemId,
            Model model) {

        lessonItemService.deleteLessonItem(lessonItemId);

        return String.format("redirect:/admin/course/%d/unit/%d/lesson/%d/item", courseId, unitId, lessonId);
    }
}

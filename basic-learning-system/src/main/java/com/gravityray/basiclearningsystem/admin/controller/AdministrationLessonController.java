package com.gravityray.basiclearningsystem.admin.controller;

import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.lesson.service.LessonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdministrationLessonController {

    private final LessonService lessonService;

    public AdministrationLessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/admin/course/{courseId}/unit/{unitId}/lesson/create")
    public String lessonCreateGet(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            Model model) {
        // TODO
        return "administration/lesson_create";
    }

    @GetMapping("/admin/course/{courseId}/unit/{unitId}/lesson/{lessonId}")
    public String lessonEditGet(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            @PathVariable Long lessonId,
            Model model) {

        // TODO

        return "administration/lesson_edit";
    }

    @GetMapping("/admin/course/{courseId}/unit/{unitId}/lesson/{lessonId}/delete")
    public String lessonDeleteGet(
            @PathVariable Long courseId,
            @PathVariable Long unitId,
            @PathVariable Long lessonId,
            Model model) {

        // TODO

        return "administration/lesson_delete";
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

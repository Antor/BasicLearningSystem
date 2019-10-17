package com.gravityray.basiclearningsystem.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdministrationLessonItemController {

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

        // TODO

        return "administration/lesson_item_delete";
    }
}

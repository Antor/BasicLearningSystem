package com.gravityray.basiclearningsystem.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdministrationUnitController {

    @GetMapping("/admin/course/{courseId}/unit/create")
    public String unitCreateGet(
            @PathVariable Long courseId,
            Model model) {
        // TODO

        return "administration/unit_create";
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

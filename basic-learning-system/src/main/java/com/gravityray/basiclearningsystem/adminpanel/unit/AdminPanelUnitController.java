package com.gravityray.basiclearningsystem.adminpanel.unit;

import com.gravityray.basiclearningsystem.adminpanel.lesson.CreateLessonForm;
import com.gravityray.basiclearningsystem.studyitem.lesson.CreateLessonFormNotValidException;
import com.gravityray.basiclearningsystem.studyitem.lesson.LessonService;
import com.gravityray.basiclearningsystem.studyitem.unit.UnitEntity;
import com.gravityray.basiclearningsystem.studyitem.unit.UnitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/admin/unit")
public class AdminPanelUnitController {

    private final UnitService unitService;
    private final LessonService lessonService;

    public AdminPanelUnitController(
            UnitService unitService,
            LessonService lessonService) {
        this.unitService = unitService;
        this.lessonService = lessonService;
    }

    @GetMapping("/{unitId}")
    public String unitEditGet(
            @PathVariable long unitId,
            Model model) {

        UnitEntity unitEntity = unitService.getUnit(unitId);

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", unitEntity.getCourse());
        model.addAttribute("unit", unitEntity);

        return "adminpanel/unit/edit";
    }

    @PostMapping("/{unitId}")
    public String unitEditPost(
            @PathVariable long unitId,
            Model model,
            EditUnitForm editUnitForm) {

        UnitEntity unitEntity = unitService.getUnit(unitId);
        try {
            unitService.updateUnit(editUnitForm);
            return String.format("redirect:/admin/course/%d/unit", unitEntity.getCourseId());

        } catch (EditUnitFormNotValidException e) {
            model.addAttribute("errors", e.getErrorList());
            model.addAttribute("course", unitEntity.getCourse());
            model.addAttribute("unit", editUnitForm);

            return "adminpanel/unit/edit";
        }
    }

    @GetMapping("/{unitId}/delete")
    public String unitDeleteGet(@PathVariable long unitId, Model model) {

        UnitEntity unitEntity = unitService.getUnit(unitId);
        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", unitEntity.getCourse());
        model.addAttribute("unit", unitEntity);

        return "adminpanel/unit/delete";
    }

    @PostMapping("/{unitId}/delete")
    public String unitDeletePost(@PathVariable long unitId) {

        long courseId = unitService.getUnit(unitId).getCourseId();

        unitService.deleteUnit(unitId);

        return String.format("redirect:/admin/course/%d/unit", courseId);
    }

    @GetMapping("/{unitId}/lesson")
    public String unitLessonList(@PathVariable long unitId, Model model) {

        UnitEntity unitEntity = unitService.getUnit(unitId);
        model.addAttribute("course", unitEntity.getCourse());
        model.addAttribute("unit", unitEntity);
        model.addAttribute("lessons", unitEntity.getLessonList());

        return "adminpanel/lesson/list";
    }

    @GetMapping("/{unitId}/lesson/create")
    public String lessonCreateGet(@PathVariable long unitId, Model model) {

        UnitEntity unitEntity = unitService.getUnit(unitId);

        model.addAttribute("errors", Collections.emptyList());
        model.addAttribute("course", unitEntity.getCourse());
        model.addAttribute("unit", unitEntity);
        model.addAttribute("lesson", new CreateLessonForm());

        return "adminpanel/lesson/create";
    }

    @PostMapping("/{unitId}/lesson/create")
    public String lessonCreatePost(
            @PathVariable long unitId,
            Model model,
            CreateLessonForm createLessonForm) {

        try {
            lessonService.createLesson(unitId, createLessonForm);
            return String.format("redirect:/admin/unit/%d/lesson", unitId);

        } catch (CreateLessonFormNotValidException e) {
            model.addAttribute("errors", e.getErrorList());

            UnitEntity unitEntity = unitService.getUnit(unitId);
            model.addAttribute("course", unitEntity.getCourse());
            model.addAttribute("unit", unitEntity);

            model.addAttribute("lesson", createLessonForm);
            return "adminpanel/lesson/create";
        }
    }
}

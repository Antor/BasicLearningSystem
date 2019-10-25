package com.gravityray.basiclearningsystem.adminpanel.unit;

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

    private final AdminPanelUnitService adminPanelUnitService;

    public AdminPanelUnitController(AdminPanelUnitService adminPanelUnitService) {
        this.adminPanelUnitService = adminPanelUnitService;
    }

    @GetMapping("/{unitId}")
    public String unitEditGet(@PathVariable long unitId, Model model) {
        model.addAttribute("course", adminPanelUnitService.getUnitEditCourseInfo(unitId));
        model.addAttribute("unitId", unitId);
        model.addAttribute("unit", adminPanelUnitService.getUnitEditInfo(unitId));
        model.addAttribute("errorList", Collections.emptyList());

        return "adminpanel/unit/edit";
    }

    @PostMapping("/{unitId}")
    public String unitEditPost(@PathVariable long unitId, Model model, UnitEditInfo unitEditInfo) {

        try {
            adminPanelUnitService.updateUnit(unitId, unitEditInfo);
            return String.format(
                    "redirect:/admin/course/%d/unit",
                    adminPanelUnitService.getUnitCourseId(unitId));

        } catch (UnitEditInfoNotValidException e) {
            model.addAttribute("course", adminPanelUnitService.getUnitEditCourseInfo(unitId));
            model.addAttribute("unitId", unitId);
            model.addAttribute("unit", unitEditInfo);
            model.addAttribute("errorList", e.getErrorList());

            return "adminpanel/unit/edit";
        }
    }

    @GetMapping("/{unitId}/delete")
    public String unitDeleteGet(@PathVariable long unitId, Model model) {
        model.addAttribute("course", adminPanelUnitService.getUnitDeleteCourseInfo(unitId));
        model.addAttribute("unitId", unitId);
        model.addAttribute("unit", adminPanelUnitService.getUnitDeleteInfo(unitId));
        model.addAttribute("errorList", Collections.emptyList());

        return "adminpanel/unit/delete";
    }

    @PostMapping("/{unitId}/delete")
    public String unitDeletePost(@PathVariable long unitId) {
        long courseId = adminPanelUnitService.getUnitCourseId(unitId);

        adminPanelUnitService.deleteUnit(unitId);

        return String.format("redirect:/admin/course/%d/unit", courseId);
    }

    @GetMapping("/{unitId}/lesson")
    public String unitLessonList(@PathVariable long unitId, Model model) {
        model.addAttribute("course", adminPanelUnitService.getLessonListCourseInfo(unitId));
        model.addAttribute("unitId", unitId);
        model.addAttribute("unit", adminPanelUnitService.getLessonListUnitInfo(unitId));
        model.addAttribute("lessonList", adminPanelUnitService.getLessonList(unitId));

        return "adminpanel/lesson/list";
    }

    @GetMapping("/{unitId}/lesson/create")
    public String lessonCreateGet(@PathVariable long unitId, Model model) {
        model.addAttribute("course", adminPanelUnitService.getLessonCreateCourseInfo(unitId));
        model.addAttribute("unitId", unitId);
        model.addAttribute("unit", adminPanelUnitService.getLessonCreateUnitInfo(unitId));
        model.addAttribute("lesson", new LessonCreateInfo());
        model.addAttribute("errorList", Collections.emptyList());

        return "adminpanel/lesson/create";
    }

    @PostMapping("/{unitId}/lesson/create")
    public String lessonCreatePost(@PathVariable long unitId, Model model, LessonCreateInfo lessonCreateInfo) {
        try {
            adminPanelUnitService.createLesson(unitId, lessonCreateInfo);
            return String.format("redirect:/admin/unit/%d/lesson", unitId);

        } catch (LessonCreateInfoNotValidException e) {
            model.addAttribute("course", adminPanelUnitService.getLessonCreateCourseInfo(unitId));
            model.addAttribute("unitId", unitId);
            model.addAttribute("unit", adminPanelUnitService.getLessonCreateUnitInfo(unitId));
            model.addAttribute("lesson", lessonCreateInfo);
            model.addAttribute("errorList", e.getErrorList());

            return "adminpanel/lesson/create";
        }
    }
}

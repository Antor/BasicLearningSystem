package com.gravityray.basiclearningsystem.adminpanel.course;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin/course")
public class AdminPanelCourseController {

    private final AdminPanelCourseService adminPanelCourseService;

    public AdminPanelCourseController(AdminPanelCourseService adminPanelCourseService) {
        this.adminPanelCourseService = adminPanelCourseService;
    }

    @GetMapping
    public String courseList(Model model) {
        model.addAttribute(
                "courseList",
                adminPanelCourseService.getCourseList());

        return "adminpanel/course/list";
    }

    @GetMapping("/create")
    public String courseCreateGet(Model model) {
        model.addAttribute("course", new CourseCreateInfo());
        model.addAttribute("errorList", new ArrayList<>());

        return "adminpanel/course/create";
    }

    @PostMapping("/create")
    public String courseCreatePost(CourseCreateInfo course, Model model) {
        try {
            adminPanelCourseService.createCourse(course);
            return "redirect:/admin/course";

        } catch (CourseCreateInfoNotValidException e) {
            model.addAttribute("course", course);
            model.addAttribute("errorList", e.getErrorList());
            return "adminpanel/course/create";
        }
    }

    @GetMapping("/{courseId}")
    public String courseEditGet(@PathVariable long courseId, Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("course", adminPanelCourseService.getCourseEditInfo(courseId));
        model.addAttribute("errorList", new ArrayList<>());

        return "adminpanel/course/edit";
    }

    @PostMapping("/{courseId}")
    public String courseEditPost(@PathVariable long courseId, Model model, CourseEditInfo course) {
        try {
            adminPanelCourseService.updateCourse(courseId, course);
            return "redirect:/admin/course";

        } catch (CourseEditInfoNotValidException e) {
            model.addAttribute("courseId", courseId);
            model.addAttribute("course", course);
            model.addAttribute("errorList", e.getErrorList());
            return "adminpanel/course/edit";

        } catch (CourseNotFoundException e) {
            List<String> errorList = new ArrayList<>();
            errorList.add("Course you're trying to edit do not exist!");

            model.addAttribute("course", course);
            model.addAttribute("errorList", errorList);
            return "adminpanel/course/edit";
        }
    }

    @GetMapping("/{courseId}/activate")
    public String courseActivateGet(@PathVariable long courseId, Model model) {
        model.addAttribute("course", adminPanelCourseService.getCourseActivateInfo(courseId));
        model.addAttribute("errorList", new ArrayList<>());

        return "adminpanel/course/activate";
    }

    @PostMapping("/{courseId}/activate")
    public String courseActivatePost(@PathVariable long courseId) {
        adminPanelCourseService.activateCourse(courseId);

        return "redirect:/admin/course";
    }

    @GetMapping("/{courseId}/deactivate")
    public String courseDeactivateGet(@PathVariable long courseId, Model model) {
        model.addAttribute("course", adminPanelCourseService.getCourseDeactivateInfo(courseId));
        model.addAttribute("errorList", new ArrayList<>());

        return "adminpanel/course/deactivate";
    }

    @PostMapping("/{courseId}/deactivate")
    public String courseDeactivatePost(@PathVariable long courseId) {
        adminPanelCourseService.deactivateCourse(courseId);

        return "redirect:/admin/course";
    }

    @GetMapping("/{courseId}/delete")
    public String courseDeleteGet(@PathVariable long courseId, Model model) {
        model.addAttribute("course", adminPanelCourseService.getCourseDeleteInfo(courseId));
        model.addAttribute("errorList", new ArrayList<>());

        return "adminpanel/course/delete";
    }

    @PostMapping("/{courseId}/delete")
    public String courseDeletePost(@PathVariable long courseId) {
        adminPanelCourseService.deleteCourse(courseId);

        return "redirect:/admin/course";
    }

    @GetMapping("/{courseId}/unit")
    public String courseUnitList(@PathVariable long courseId, Model model) {
        model.addAttribute("course", adminPanelCourseService.getUnitListCourseInfo(courseId));
        model.addAttribute("unitList", adminPanelCourseService.getUnitList(courseId));

        return "adminpanel/unit/list";
    }

    @GetMapping("/{courseId}/unit/create")
    public String unitCreateGet(@PathVariable long courseId, Model model) {
        model.addAttribute("course", adminPanelCourseService.getUnitCreateCourseInfo(courseId));
        model.addAttribute("unit", new UnitCreateInfo());
        model.addAttribute("errorList", Collections.emptyList());

        return "adminpanel/unit/create";
    }

    @PostMapping("/{courseId}/unit/create")
    public String unitCreatePost(@PathVariable long courseId, Model model, UnitCreateInfo unitCreateInfo) {
        try {
            adminPanelCourseService.createUnit(courseId, unitCreateInfo);
            return String.format("redirect:/admin/course/%d/unit", courseId);

        } catch (UnitCreateInfoNotValidException e) {
            model.addAttribute("course", adminPanelCourseService.getUnitCreateCourseInfo(courseId));
            model.addAttribute("unit", unitCreateInfo);
            model.addAttribute("errors", e.getErrorList());
            return "adminpanel/unit/create";
        }
    }

}

package com.gravityray.basiclearningsystem.study;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study")
public class StudyController {

    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("/course/{courseId}")
    public String course(
            @PathVariable long courseId,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("course", studyService.getStudyCourse(courseId));
        model.addAttribute("courseTree",
                studyService.getCourseTreeByCourseId(userDetails.getUsername(), courseId));
        return "study/course";
    }

    @GetMapping("/unit/{unitId}")
    public String unit(
            @PathVariable long unitId,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("unit", studyService.getStudyUnit(unitId));
        model.addAttribute("courseTree",
                studyService.getCourseTreeByUnitId(userDetails.getUsername(), unitId));
        return "study/unit";
    }

    @GetMapping("/lesson/{lessonId}")
    public String lesson(
            @PathVariable long lessonId,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("lesson", studyService.getStudyLesson(lessonId));
        model.addAttribute("courseTree",
                studyService.getCourseTreeByLessonId(userDetails.getUsername(), lessonId));
        return "study/lesson";
    }

    @GetMapping("/lessonitem/{lessonItemId}")
    public String lessonItem(
            @PathVariable long lessonItemId,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("lessonItem", studyService.getStudyLessonItem(userDetails.getUsername(), lessonItemId));
        model.addAttribute("courseTree",
                studyService.getCourseTreeByLessonItemId(userDetails.getUsername(), lessonItemId));
        return "study/lesson_item";
    }

    @GetMapping("/lessonitem/{lessonItemId}/complete")
    public String completeLessonItemGet(
            @PathVariable long lessonItemId,
            Model model) {
        model.addAttribute("lessonItem", studyService.getCompleteLessonItemInfo(lessonItemId));
        return "study/complete_lesson_item";
    }

    @PostMapping("/lessonitem/{lessonItemId}/complete")
    public String completeLessonItemPost(@PathVariable long lessonItemId,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        studyService.completeLessonItem(userDetails.getUsername(), lessonItemId);
        return String.format("redirect:/study/lessonitem/%d", lessonItemId);
    }

}

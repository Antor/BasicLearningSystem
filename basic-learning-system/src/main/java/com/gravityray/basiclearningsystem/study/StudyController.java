package com.gravityray.basiclearningsystem.study;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            Model model) {
        model.addAttribute("course", studyService.getStudyCourse(courseId));
        model.addAttribute("courseTree", studyService.getCourseTreeByCourseId(courseId));
        return "study/course";
    }

    @GetMapping("/unit/{unitId}")
    public String unit(
            @PathVariable long unitId,
            Model model) {
        model.addAttribute("unit", studyService.getStudyUnit(unitId));
        model.addAttribute("courseTree", studyService.getCourseTreeByUnitId(unitId));
        return "study/unit";
    }

    @GetMapping("/lesson/{lessonId}")
    public String lesson(
            @PathVariable long lessonId,
            Model model) {
        model.addAttribute("lesson", studyService.getStudyLesson(lessonId));
        model.addAttribute("courseTree", studyService.getCourseTreeByLessonId(lessonId));
        return "study/lesson";
    }

    @GetMapping("/lessonitem/{lessonItemId}")
    public String lessonItem(
            @PathVariable long lessonItemId,
            Model model) {
        model.addAttribute("lessonItem", studyService.getStudyLessonItem(lessonItemId));
        model.addAttribute("courseTree", studyService.getCourseTreeByLessonItemId(lessonItemId));
        return "study/lesson_item";
    }

}

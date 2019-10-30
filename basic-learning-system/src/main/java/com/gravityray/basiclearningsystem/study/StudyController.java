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

    @GetMapping("/course/{courseId}/next")
    public String courseMoveToNext(@PathVariable long courseId) {
        return buildStudyItemRedirect(
                studyService.getNextStudyItemForCourse(courseId));
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

    @GetMapping("/unit/{unitId}/prev")
    public String unitMoveToPrev(@PathVariable long unitId) {
        return buildStudyItemRedirect(
                studyService.getPrevStudyItemForUnit(unitId));
    }

    @GetMapping("/unit/{unitId}/next")
    public String unitMoveToNext(@PathVariable long unitId) {
        return buildStudyItemRedirect(
                studyService.getNextStudyItemForUnit(unitId));
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

    @GetMapping("/lesson/{lessonId}/prev")
    public String lessonMoveToPrev(@PathVariable long lessonId) {
        return buildStudyItemRedirect(
                studyService.getPrevStudyItemForLesson(lessonId));
    }

    @GetMapping("/lesson/{lessonId}/next")
    public String lessonMoveToNext(@PathVariable long lessonId) {
        return buildStudyItemRedirect(
                studyService.getNextStudyItemForLesson(lessonId));
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

    @GetMapping("/lessonitem/{lessonItemId}/prev")
    public String lessonItemMoveToPrev(@PathVariable long lessonItemId) {
        return buildStudyItemRedirect(
                studyService.getPrevStudyItemForLessonItem(lessonItemId));
    }

    @GetMapping("/lessonitem/{lessonItemId}/next")
    public String lessonItemMoveToNext(@PathVariable long lessonItemId) {
        return buildStudyItemRedirect(
                studyService.getNextStudyItemForLessonItem(lessonItemId));
    }

    @GetMapping("/lessonitem/{lessonItemId}/complete")
    public String completeLessonItemGet(
            @PathVariable long lessonItemId,
            Model model) {
        model.addAttribute("lessonItem", studyService.getCompleteLessonItemInfo(lessonItemId));
        return "study/complete_lesson_item";
    }

    @PostMapping("/lessonitem/{lessonItemId}/complete")
    public String completeLessonItemPost(
            @PathVariable long lessonItemId,
            @AuthenticationPrincipal UserDetails userDetails) {
        studyService.completeLessonItem(userDetails.getUsername(), lessonItemId);
        return String.format("redirect:/study/lessonitem/%d", lessonItemId);
    }

    private String buildStudyItemRedirect(StudyItemNavigationInfo navigationInfo) {
        String redirect;
        switch (navigationInfo.getType()) {
            case StudyItemNavigationInfo.TYPE_COURSE:
                redirect = String.format("redirect:/study/course/%d", navigationInfo.getCourseId());
                break;
            case StudyItemNavigationInfo.TYPE_UNIT:
                redirect = String.format("redirect:/study/unit/%d", navigationInfo.getUnitId());
                break;
            case StudyItemNavigationInfo.TYPE_LESSON:
                redirect = String.format("redirect:/study/lesson/%d", navigationInfo.getLessonId());
                break;
            case StudyItemNavigationInfo.TYPE_LESSON_ITEM:
                redirect = String.format("redirect:/study/lessonitem/%d", navigationInfo.getLessonItemId());
                break;
            default:
                throw new RuntimeException("Unknown study item type: " + navigationInfo.getType());
        }
        return redirect;
    }

}

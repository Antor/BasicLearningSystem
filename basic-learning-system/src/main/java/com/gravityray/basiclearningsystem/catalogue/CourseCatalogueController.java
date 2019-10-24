package com.gravityray.basiclearningsystem.catalogue;

import com.gravityray.basiclearningsystem.enrolment.EnrolmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/catalogue/course")
public class CourseCatalogueController {

    private static final String TARGET_CATALOGUE = "catalogue";
    private static final String TARGET_PROFILE = "profile";

    private final CourseCatalogueService courseCatalogueService;
    private final EnrolmentService enrolmentService;

    public CourseCatalogueController(
            CourseCatalogueService courseCatalogueService,
            EnrolmentService enrolmentService) {
        this.courseCatalogueService = courseCatalogueService;
        this.enrolmentService = enrolmentService;
    }

    @GetMapping
    public String catalogue(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails != null ? userDetails.getUsername() : null;
        model.addAttribute(
                "catalogue",
                courseCatalogueService.getCourseCatalogue(email));
        return "catalogue";
    }

    @GetMapping("/{courseId}/enrol")
    public String courseEnrolGet(
            @PathVariable long courseId,
            @RequestParam("target") String target,
            @AuthenticationPrincipal UserDetails userDetails,
            Model model) {
        model.addAttribute(
                "course",
                enrolmentService.getCourseEnrolmentInfo(courseId, userDetails.getUsername()));
        model.addAttribute("target", target);

        return "course_enrol";
    }

    @PostMapping("{courseId}/enrol")
    public String courseEnrolPost(
            @PathVariable long courseId,
            @RequestParam("target") String target,
            @AuthenticationPrincipal UserDetails userDetails) {
        enrolmentService.enrolCourse(courseId, userDetails.getUsername());

        return getTargetRedirect(target);
    }

    @GetMapping("/{courseId}/leave")
    public String courseLeaveGet(
            @PathVariable long courseId,
            @RequestParam("target") String target,
            @AuthenticationPrincipal UserDetails userDetails,
            Model model) {
        model.addAttribute(
                "course",
                enrolmentService.getCourseEnrolmentInfo(courseId, userDetails.getUsername()));
        model.addAttribute("target", target);

        return "course_leave";
    }

    @PostMapping("/{courseId}/leave")
    public String courseLeavePost(
            @PathVariable long courseId,
            @RequestParam("target") String target,
            @AuthenticationPrincipal UserDetails userDetails) {
        enrolmentService.leaveCourse(courseId, userDetails.getUsername());

        return getTargetRedirect(target);
    }

    private String getTargetRedirect(String target) {
        switch (StringUtils.defaultString(target)) {
            case TARGET_PROFILE:
                return "redirect:/profile/course";
            case TARGET_CATALOGUE:
            default:
                return "redirect:/catalogue/course";
        }
    }
}

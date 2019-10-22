package com.gravityray.basiclearningsystem.user.profile;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public String profile(Model model, @AuthenticationPrincipal UserDetails userDetails)
            throws ProfileNotFoundException {
        model.addAttribute(
                "profile",
                profileService.getViewProfileInfo(userDetails.getUsername()));
        return "profile/main";
    }

    @GetMapping("/edit")
    public String profileEditGet(Model model, @AuthenticationPrincipal UserDetails userDetails)
            throws ProfileNotFoundException {
        model.addAttribute("errors", new ArrayList<>());

        model.addAttribute(
                "profile",
                profileService.getEditProfileForm(userDetails.getUsername()));

        return "profile/edit";
    }

    @PostMapping("/edit")
    public String profileEditPost(Model model, EditProfileForm profileForm)
            throws ProfileNotFoundException {
        try {
            profileService.updateProfile(profileForm);
            return "redirect:/profile";

        } catch (EditProfileFormNotValidException e) {
            model.addAttribute("profile", profileForm);
            model.addAttribute("errors", e.getErrorMessageList());
            return "profile/edit";
        }
    }

    @GetMapping("/course")
    public String profileCourseList(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute(
                "info",
                profileService.getProfileCourseListInfo(userDetails.getUsername()));
        return "profile/course_list";
    }

    @GetMapping("/delete")
    public String profileDeleteGet(
            Model model,
            @AuthenticationPrincipal UserDetails userDetails) throws ProfileNotFoundException {
        model.addAttribute(
                "profile",
                profileService.getDeleteProfileInfo(userDetails.getUsername()));

        return "profile/delete";
    }

    @PostMapping("/delete")
    public String profileDeletePost(
            HttpServletRequest request,
            DeleteProfileInfo deleteProfileInfo) throws ServletException {
        profileService.deleteProfile(deleteProfileInfo);

        request.logout();

        return "redirect:/";
    }
}

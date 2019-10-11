package com.gravityray.basiclearningsystem.profile;

import com.gravityray.basiclearningsystem.profile.model.DeleteProfileInfo;
import com.gravityray.basiclearningsystem.profile.model.EditProfileForm;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal UserDetails userDetails)
            throws ProfileNotFoundException {
        model.addAttribute(
                "profile",
                profileService.getViewProfileInfo(userDetails.getUsername()));
        return "profile/profile";
    }

    @GetMapping("/profile/edit")
    public String profileEditGet(Model model, @AuthenticationPrincipal UserDetails userDetails)
            throws ProfileNotFoundException {
        model.addAttribute("errors", new ArrayList<>());

        model.addAttribute(
                "profile",
                profileService.getEditProfileForm(userDetails.getUsername()));

        return "profile/profile_edit";
    }

    @PostMapping("/profile/edit")
    public String profileEditPost(Model model, EditProfileForm profileForm)
            throws ProfileNotFoundException {
        try {
            profileService.updateProfile(profileForm);
            return "redirect:/profile";

        } catch (EditProfileFormNotValidException e) {
            model.addAttribute("profile", profileForm);
            model.addAttribute("errors", e.getErrorMessageList());
            return "profile/profile_edit";
        }
    }

    @GetMapping("/profile/course")
    public String profileCourseList() {

        return "profile/profile_course_list";
    }

    @GetMapping("/profile/delete")
    public String profileDeleteGet(
            Model model,
            @AuthenticationPrincipal UserDetails userDetails) throws ProfileNotFoundException {
        model.addAttribute(
                "profile",
                profileService.getDeleteProfileInfo(userDetails.getUsername()));

        return "profile/profile_delete";
    }

    @PostMapping("/profile/delete")
    public String profileDeletePost(
            HttpServletRequest request,
            DeleteProfileInfo deleteProfileInfo) throws ServletException {
        profileService.deleteProfile(deleteProfileInfo);

        request.logout();

        return "redirect:/";
    }
}

package com.gravityray.basiclearningsystem.user.controller.ui;

import com.gravityray.basiclearningsystem.user.model.entity.UserEntity;
import com.gravityray.basiclearningsystem.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity userEntity = userService.getUser(authentication.getName());

        String name = String.format("%s %s", userEntity.getFirstName(), userEntity.getLastName());
        model.addAttribute("name", name);
        model.addAttribute("email", userEntity.getEmail());

        return "profile";
    }

    @GetMapping("/profile/edit")
    public String profileEdit() {

        return "profile_edit";
    }

    @GetMapping("/profile/course")
    public String profileCourseList() {

        return "profile_course_list";
    }

    @GetMapping("/profile/delete")
    public String profileDelete() {

        return "profile_delete";
    }
}

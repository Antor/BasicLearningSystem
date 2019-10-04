package com.gravityray.basiclearningsystem.admin.controller.ui;

import com.gravityray.basiclearningsystem.user.controller.ui.UserInfoModelAppender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministrationController {

    private final UserInfoModelAppender userInfoModelAppender;

    public AdministrationController(UserInfoModelAppender userInfoModelAppender) {
        this.userInfoModelAppender = userInfoModelAppender;
    }

    @GetMapping("/admin")
    public String administration(Model model) {

        userInfoModelAppender.append(model);

        return "administration";
    }

    @GetMapping("/admin/courses")
    public String administrationCourses(Model model) {

        userInfoModelAppender.append(model);

        return "administration_courses";
    }

    @GetMapping("/admin/users")
    public String administrationUsers(Model model) {

        userInfoModelAppender.append(model);

        return "administration_users";
    }
}

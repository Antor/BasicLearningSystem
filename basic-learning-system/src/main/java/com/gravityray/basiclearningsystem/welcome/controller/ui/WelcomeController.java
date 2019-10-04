package com.gravityray.basiclearningsystem.welcome.controller.ui;

import com.gravityray.basiclearningsystem.user.controller.ui.UserInfoModelAppender;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WelcomeController {

    private final UserInfoModelAppender userInfoModelAppender;

    public WelcomeController(UserInfoModelAppender userInfoModelAppender) {
        this.userInfoModelAppender = userInfoModelAppender;
    }

    @GetMapping("/")
    public String welcome(Model model) {
        userInfoModelAppender.append(model);


        return "welcome";
    }
}

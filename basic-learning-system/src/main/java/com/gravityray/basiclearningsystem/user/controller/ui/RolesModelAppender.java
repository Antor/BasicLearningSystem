package com.gravityray.basiclearningsystem.user.controller.ui;

import com.gravityray.basiclearningsystem.user.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class RolesModelAppender {

    private final UserService userService;

    public RolesModelAppender(UserService userService) {
        this.userService = userService;
    }

    public void append(Model model) {
        model.addAttribute("roles", userService.getAllRoles());
    }
}

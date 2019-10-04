package com.gravityray.basiclearningsystem.user.controller.ui;

import com.gravityray.basiclearningsystem.user.model.entity.UserEntity;
import com.gravityray.basiclearningsystem.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class UserInfoModelAppender {

    private final UserService userService;

    public UserInfoModelAppender(UserService userService) {
        this.userService = userService;
    }

    public void append(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity userEntity = userService.getUser(authentication.getName());
        boolean authenticated = userEntity != null;
        model.addAttribute("authenticated", authenticated);

        if (authenticated) {
            model.addAttribute("username", userEntity.getFirstName());
        }
    }
}

package com.gravityray.basiclearningsystem.user.controller.ui;

import com.gravityray.basiclearningsystem.user.model.entity.UserEntity;
import com.gravityray.basiclearningsystem.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserInfoControllerAdvice {

    private final UserService userService;

    public UserInfoControllerAdvice(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void appendUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity userEntity = userService.getUser(authentication.getName());
        boolean authenticated = userEntity != null;
        model.addAttribute("authenticated", authenticated);

        if (authenticated) {
            String role = userEntity.getRole();
            boolean admin = role != null && role.equalsIgnoreCase("admin");
            model.addAttribute("isAdmin", admin);
            model.addAttribute("username", userEntity.getFirstName());
        }
    }
}

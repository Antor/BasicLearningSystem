package com.gravityray.basiclearningsystem.welcome;

import com.gravityray.basiclearningsystem.user.model.UserEntity;
import com.gravityray.basiclearningsystem.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    private final UserService userService;

    public WelcomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String welcome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity userEntity = userService.getUser(authentication.getName());
        boolean authenticated = userEntity != null;
        model.addAttribute("authenticated", authenticated);

        if (authenticated) {
            model.addAttribute("username", userEntity.getFirstName());
        }

        return "welcome";
    }
}

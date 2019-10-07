package com.gravityray.basiclearningsystem.user.controller.ui;

import com.gravityray.basiclearningsystem.user.converter.UserConverter;
import com.gravityray.basiclearningsystem.user.model.dto.UserDto;
import com.gravityray.basiclearningsystem.user.model.entity.Role;
import com.gravityray.basiclearningsystem.user.model.entity.UserEntity;
import com.gravityray.basiclearningsystem.user.service.UserService;
import com.gravityray.basiclearningsystem.user.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterController {

    private final UserService userService;
    private final UserConverter userConverter;
    private final UserValidator userValidator;

    public RegisterController(
            UserService userService,
            UserConverter userConverter,
            UserValidator userValidator) {
        this.userService = userService;
        this.userConverter = userConverter;
        this.userValidator = userValidator;
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("errors", new ArrayList<String>());

        UserDto user = new UserDto();
        user.setRole(Role.ID_STUDENT);
        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping("register")
    public String register(
            HttpServletRequest request,
            Model model,
            UserDto user) {
        List<String> verificationErrors = userValidator.validateUser(user);

        if (verificationErrors.size() > 0) {
            model.addAttribute("errors", verificationErrors);
            model.addAttribute("user", user);
            return "register";
        }

        try {
            UserEntity userEntity = userConverter.toUserEntity(user);
            userService.createUser(userEntity);

        } catch (Exception e) {
            List<String> createUserErrors = new ArrayList<>();
            createUserErrors.add(e.getMessage());
            model.addAttribute("errors", createUserErrors);
            model.addAttribute("user", user);
            return "register";
        }

        try {
            request.login(user.getEmail(), user.getPassword());
        } catch (ServletException e) {
            // ignore
        }

        return "redirect:/";
    }
}

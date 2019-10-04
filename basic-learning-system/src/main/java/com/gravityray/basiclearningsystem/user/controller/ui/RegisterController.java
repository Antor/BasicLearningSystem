package com.gravityray.basiclearningsystem.user.controller.ui;

import com.gravityray.basiclearningsystem.user.model.dto.RegistrationFormDto;
import com.gravityray.basiclearningsystem.user.model.entity.UserEntity;
import com.gravityray.basiclearningsystem.user.service.UserService;
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

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("register")
    public String register(Model model) {

        model.addAttribute("errors", new ArrayList<String>());
        model.addAttribute("registrationForm", new RegistrationFormDto());

        return "register";
    }

    @PostMapping("register")
    public String register(
            HttpServletRequest request,
            Model model,
            RegistrationFormDto registrationForm) {
        System.out.println("Register request: " + registrationForm.toString());

        List<String> verificationErrors = validateRegistrationForm(registrationForm);
        if (verificationErrors.size() > 0) {
            model.addAttribute("errors", verificationErrors);
            model.addAttribute("registrationForm", registrationForm);
            return "register";
        }

        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName(registrationForm.getFirstName());
            userEntity.setLastName(registrationForm.getLastName());
            userEntity.setEmail(registrationForm.getEmail());
            userEntity.setPassword(registrationForm.getPassword());
            userEntity.setRole("student");
            userService.createUser(userEntity);

        } catch (Exception e) {
            List<String> createUserErrors = new ArrayList<>();
            createUserErrors.add(e.getMessage());
            model.addAttribute("errors", verificationErrors);
            model.addAttribute("registrationForm", registrationForm);
            return "register";
        }

        try {
            request.login(registrationForm.getEmail(), registrationForm.getPassword());
        } catch (ServletException e) {
            // ignore
        }

        return "redirect:/";
    }

    private List<String> validateRegistrationForm(RegistrationFormDto registrationForm) {
        List<String> errors = new ArrayList<>();

        if (registrationForm.getFirstName().trim().isEmpty()) {
            errors.add("Missing First Name");
        }

        if (registrationForm.getLastName().trim().isEmpty()) {
            errors.add("Missing Last Name");
        }

        if (registrationForm.getEmail().trim().isEmpty()) {
            errors.add("Missing Email");
        }

        if (registrationForm.getPassword().trim().isEmpty()) {
            errors.add("Missing Password");
        }

        return errors;
    }
}

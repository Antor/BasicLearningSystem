package com.gravityray.basiclearningsystem.admin.controller;

import com.gravityray.basiclearningsystem.user.controller.RolesModelAppender;
import com.gravityray.basiclearningsystem.unit.service.UserConverter;
import com.gravityray.basiclearningsystem.user.model.UserDto;
import com.gravityray.basiclearningsystem.user.model.UserEntity;
import com.gravityray.basiclearningsystem.user.service.UserService;
import com.gravityray.basiclearningsystem.user.service.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdministrationUsersController {

    private final UserService userService;
    private final RolesModelAppender rolesModelAppender;
    private final UserValidator userValidator;
    private final UserConverter userConverter;

    public AdministrationUsersController(
            UserService userService,
            RolesModelAppender rolesModelAppender,
            UserValidator userValidator,
            UserConverter userConverter) {
        this.userService = userService;
        this.rolesModelAppender = rolesModelAppender;
        this.userValidator = userValidator;
        this.userConverter = userConverter;
    }

    @GetMapping("/admin/user")
    public String administrationUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "administration/administration_users";
    }

    @GetMapping("/admin/user/{userId}")
    public String userEditGet(@PathVariable Long userId, Model model) {
        rolesModelAppender.append(model);

        UserEntity userEntity = userService.getUser(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("errors", new ArrayList<>());
        model.addAttribute("user", userConverter.toUserDto(userEntity));

        return "administration/user_edit";
    }

    @PostMapping("/admin/user/{userId}")
    public String userEditPost(
            @PathVariable Long userId,
            Model model,
            UserDto user) {
        List<String> verificationErrors = userValidator.validateEditUser(user);
        if (verificationErrors.size() > 0) {
            rolesModelAppender.append(model);

            model.addAttribute("errors", verificationErrors);
            model.addAttribute("user", user);

            return "administration/user_edit";
        }

        try {
            UserEntity userEntity = userService.getUser(userId);
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userEntity.setRole(user.getRole());
            userService.updateUser(userEntity);

        } catch (Exception e) {
            rolesModelAppender.append(model);

            List<String> createUserErrors = new ArrayList<>();
            createUserErrors.add(e.getMessage());
            model.addAttribute("errors", createUserErrors);
            model.addAttribute("user", user);
            return "administration/user_edit";
        }

        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/create")
    public String userCreateGet(Model model) {
        rolesModelAppender.append(model);

        model.addAttribute("errors", new ArrayList<>());
        model.addAttribute("user", new UserDto());

        return "administration/user_create";
    }

    @PostMapping("/admin/user/create")
    public String userCreatePost(Model model, UserDto user) {
        List<String> verificationErrors = userValidator.validateCreateUser(user);
        if (verificationErrors.size() > 0) {
            rolesModelAppender.append(model);

            model.addAttribute("errors", verificationErrors);
            model.addAttribute("user", user);

            return "administration/user_create";
        }

        try {
            UserEntity userEntity = userConverter.toUserEntity(user);
            userService.createUser(userEntity);

        } catch (Exception e) {
            rolesModelAppender.append(model);

            List<String> createUserErrors = new ArrayList<>();
            createUserErrors.add(e.getMessage());
            model.addAttribute("errors", createUserErrors);
            model.addAttribute("user", user);
            return "administration/user_create";
        }

        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/{userId}/delete")
    public String userDeleteGet(@PathVariable Long userId, Model model) {

        UserEntity userEntity = userService.getUser(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("errors", new ArrayList<>());
        model.addAttribute("user", userConverter.toUserDto(userEntity));

        return "administration/user_delete";
    }

    @PostMapping("/admin/user/{userId}/delete")
    public String userDeletePost(@PathVariable Long userId, Model model) {

        try {
            userService.deleteUser(userId);

        } catch (Exception e) {
            rolesModelAppender.append(model);

            List<String> createUserErrors = new ArrayList<>();
            createUserErrors.add(e.getMessage());
            model.addAttribute("errors", createUserErrors);
            UserEntity userEntity = userService.getUser(userId);
            model.addAttribute("user", userConverter.toUserDto(userEntity));
            return "administration/user_delete";
        }

        return "redirect:/admin/user";
    }
}

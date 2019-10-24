package com.gravityray.basiclearningsystem.adminpanel.user;

import com.gravityray.basiclearningsystem.user.RolesModelAppender;
import com.gravityray.basiclearningsystem.user.UserConverter;
import com.gravityray.basiclearningsystem.user.UserDto;
import com.gravityray.basiclearningsystem.user.UserEntity;
import com.gravityray.basiclearningsystem.user.UserService;
import com.gravityray.basiclearningsystem.user.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class AdminPanelUserController {

    private final UserService userService;
    private final RolesModelAppender rolesModelAppender;
    private final UserValidator userValidator;
    private final UserConverter userConverter;

    public AdminPanelUserController(
            UserService userService,
            RolesModelAppender rolesModelAppender,
            UserValidator userValidator,
            UserConverter userConverter) {
        this.userService = userService;
        this.rolesModelAppender = rolesModelAppender;
        this.userValidator = userValidator;
        this.userConverter = userConverter;
    }

    @GetMapping
    public String administrationUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "adminpanel/user/list";
    }

    @GetMapping("/{userId}")
    public String userEditGet(@PathVariable Long userId, Model model) {
        rolesModelAppender.append(model);

        UserEntity userEntity = userService.getUser(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("errors", new ArrayList<>());
        model.addAttribute("user", userConverter.toUserDto(userEntity));

        return "adminpanel/user/edit";
    }

    @PostMapping("/{userId}")
    public String userEditPost(
            @PathVariable Long userId,
            Model model,
            UserDto user) {
        List<String> verificationErrors = userValidator.validateEditUser(user);
        if (verificationErrors.size() > 0) {
            rolesModelAppender.append(model);

            model.addAttribute("errors", verificationErrors);
            model.addAttribute("user", user);

            return "adminpanel/user/edit";
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
            return "adminpanel/user_edit";
        }

        return "redirect:/admin/user";
    }

    @GetMapping("/create")
    public String userCreateGet(Model model) {
        rolesModelAppender.append(model);

        model.addAttribute("errors", new ArrayList<>());
        model.addAttribute("user", new UserDto());

        return "adminpanel/user/create";
    }

    @PostMapping("/create")
    public String userCreatePost(Model model, UserDto user) {
        List<String> verificationErrors = userValidator.validateCreateUser(user);
        if (verificationErrors.size() > 0) {
            rolesModelAppender.append(model);

            model.addAttribute("errors", verificationErrors);
            model.addAttribute("user", user);

            return "adminpanel/user/create";
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
            return "adminpanel/user/create";
        }

        return "redirect:/admin/user";
    }

    @GetMapping("/{userId}/delete")
    public String userDeleteGet(@PathVariable Long userId, Model model) {

        UserEntity userEntity = userService.getUser(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("errors", new ArrayList<>());
        model.addAttribute("user", userConverter.toUserDto(userEntity));

        return "adminpanel/user/delete";
    }

    @PostMapping("/{userId}/delete")
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
            return "adminpanel/user/delete";
        }

        return "redirect:/admin/user";
    }
}

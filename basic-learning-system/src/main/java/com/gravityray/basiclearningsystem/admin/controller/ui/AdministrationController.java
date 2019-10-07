package com.gravityray.basiclearningsystem.admin.controller.ui;

import com.gravityray.basiclearningsystem.course.service.CourseService;
import com.gravityray.basiclearningsystem.user.controller.ui.RolesModelAppender;
import com.gravityray.basiclearningsystem.user.controller.ui.UserInfoModelAppender;
import com.gravityray.basiclearningsystem.user.converter.UserConverter;
import com.gravityray.basiclearningsystem.user.model.dto.UserDto;
import com.gravityray.basiclearningsystem.user.model.entity.UserEntity;
import com.gravityray.basiclearningsystem.user.service.UserService;
import com.gravityray.basiclearningsystem.user.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdministrationController {

    private final UserService userService;
    private final CourseService courseService;
    private final UserInfoModelAppender userInfoModelAppender;
    private final RolesModelAppender rolesModelAppender;
    private final UserValidator userValidator;
    private final UserConverter userConverter;

    public AdministrationController(
            UserService userService,
            CourseService courseService,
            UserInfoModelAppender userInfoModelAppender,
            RolesModelAppender rolesModelAppender,
            UserValidator userValidator,
            UserConverter userConverter) {
        this.userService = userService;
        this.courseService = courseService;
        this.userInfoModelAppender = userInfoModelAppender;
        this.rolesModelAppender = rolesModelAppender;
        this.userValidator = userValidator;
        this.userConverter = userConverter;
    }

    @GetMapping("/admin")
    public String administration(Model model) {

        userInfoModelAppender.append(model);

        return "administration/administration";
    }

    @GetMapping("/admin/course")
    public String administrationCourses(Model model) {

        userInfoModelAppender.append(model);

        model.addAttribute("courses", courseService.getCourses(false));

        return "administration/administration_courses";
    }

    @GetMapping("/admin/course/create")
    public String courseCreate(Model model) {
        userInfoModelAppender.append(model);

        return "administration/course_create";
    }

    @GetMapping("/admin/user")
    public String administrationUsers(Model model) {

        userInfoModelAppender.append(model);

        model.addAttribute("users", userService.getAllUsers());

        return "administration/administration_users";
    }

    @GetMapping("/admin/user/create")
    public String userCreateGet(Model model) {
        userInfoModelAppender.append(model);
        rolesModelAppender.append(model);

        model.addAttribute("errors", new ArrayList<>());
        model.addAttribute("user", new UserDto());

        return "administration/user_create";
    }

    @PostMapping("/admin/user/create")
    public String userCreatePost(Model model, UserDto user) {
        userInfoModelAppender.append(model);
        rolesModelAppender.append(model);

        List<String> verificationErrors = userValidator.validateUser(user);

        if (verificationErrors.size() > 0) {
            model.addAttribute("errors", verificationErrors);
            model.addAttribute("user", user);
            return "administration/user_create";
        }

        try {
            UserEntity userEntity = userConverter.toUserEntity(user);
            userService.createUser(userEntity);

        } catch (Exception e) {
            List<String> createUserErrors = new ArrayList<>();
            createUserErrors.add(e.getMessage());
            model.addAttribute("errors", createUserErrors);
            model.addAttribute("user", user);
            return "administration/user_create";
        }

        return "redirect:/admin/user";
    }
}

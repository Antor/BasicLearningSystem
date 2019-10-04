package com.gravityray.basiclearningsystem.admin.controller.ui;

import com.gravityray.basiclearningsystem.course.service.CourseService;
import com.gravityray.basiclearningsystem.user.controller.ui.UserInfoModelAppender;
import com.gravityray.basiclearningsystem.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministrationController {

    private final UserService userService;
    private final CourseService courseService;
    private final UserInfoModelAppender userInfoModelAppender;

    public AdministrationController(
            UserService userService,
            CourseService courseService,
            UserInfoModelAppender userInfoModelAppender) {
        this.userService = userService;
        this.courseService = courseService;
        this.userInfoModelAppender = userInfoModelAppender;
    }

    @GetMapping("/admin")
    public String administration(Model model) {

        userInfoModelAppender.append(model);

        return "administration";
    }

    @GetMapping("/admin/course")
    public String administrationCourses(Model model) {

        userInfoModelAppender.append(model);

        model.addAttribute("courses", courseService.getCourses(false));

        return "administration_courses";
    }

    @GetMapping("/admin/course/create")
    public String courseCreate(Model model) {
        userInfoModelAppender.append(model);

        return "course_create";
    }

    @GetMapping("/admin/user")
    public String administrationUsers(Model model) {

        userInfoModelAppender.append(model);

        model.addAttribute("users", userService.getAllUsers());

        return "administration_users";
    }

    @GetMapping("/admin/user/create")
    public String userCreate(Model model) {
        userInfoModelAppender.append(model);

        return "user_create";
    }
}

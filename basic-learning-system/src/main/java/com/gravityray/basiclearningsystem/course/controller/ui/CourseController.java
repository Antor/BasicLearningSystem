package com.gravityray.basiclearningsystem.course.controller.ui;

import com.gravityray.basiclearningsystem.course.model.CourseEntity;
import com.gravityray.basiclearningsystem.course.service.CourseService;
import com.gravityray.basiclearningsystem.user.model.entity.UserEntity;
import com.gravityray.basiclearningsystem.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CourseController {

    private final UserService userService;
    private final CourseService courseService;

    public CourseController(
            UserService userService,
            CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping("/course")
    public String courseList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity userEntity = userService.getUser(authentication.getName());
        boolean authenticated = userEntity != null;
        model.addAttribute("authenticated", authenticated);

        if (authenticated) {
            model.addAttribute("username", userEntity.getFirstName());
        }

        List<CourseEntity> courseList = courseService.getCourses(true);
        model.addAttribute("courses", courseList);

        return "course_list";
    }

}

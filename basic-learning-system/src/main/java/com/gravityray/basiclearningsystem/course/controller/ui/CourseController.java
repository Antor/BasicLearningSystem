package com.gravityray.basiclearningsystem.course.controller.ui;

import com.gravityray.basiclearningsystem.course.service.CourseService;
import com.gravityray.basiclearningsystem.user.controller.ui.UserInfoModelAppender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    private final CourseService courseService;
    private final UserInfoModelAppender userInfoModelAppender;

    public CourseController(
            CourseService courseService,
            UserInfoModelAppender userInfoModelAppender) {
        this.courseService = courseService;
        this.userInfoModelAppender = userInfoModelAppender;
    }

    @GetMapping("/course")
    public String courseList(Model model) {
        userInfoModelAppender.append(model);

        model.addAttribute("courses", courseService.getCourses(true));

        return "course_list";
    }

}

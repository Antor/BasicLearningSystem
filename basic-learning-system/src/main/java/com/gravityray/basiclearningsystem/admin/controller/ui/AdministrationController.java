package com.gravityray.basiclearningsystem.admin.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministrationController {

    @GetMapping("/admin")
    public String administration() {
        return "administration/administration";
    }


}

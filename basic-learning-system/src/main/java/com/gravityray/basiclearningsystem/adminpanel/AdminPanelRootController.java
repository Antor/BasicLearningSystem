package com.gravityray.basiclearningsystem.adminpanel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPanelRootController {

    @GetMapping
    public String administration() {
        return "adminpanel/main";
    }

}

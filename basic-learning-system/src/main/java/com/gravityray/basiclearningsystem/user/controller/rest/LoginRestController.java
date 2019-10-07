package com.gravityray.basiclearningsystem.user.controller.rest;

import com.gravityray.basiclearningsystem.user.model.dto.LoginRequestDto;
import com.gravityray.basiclearningsystem.user.model.dto.LoginResponseDto;
import com.gravityray.basiclearningsystem.user.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginRestController {

    private final UserService userService;

    public LoginRestController(
            @Qualifier("persistent") UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        String authToken = userService.loginUser(request.getEmail(), request.getPassword());

        LoginResponseDto response = new LoginResponseDto();
        response.setAuthToken(authToken);
        return response;
    }

    @PostMapping("/logout")
    public void logout() {
        // TODO: figure out what it should do and if it ever required at all
    }
}

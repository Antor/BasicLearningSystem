package com.gravityray.basiclearningsystem.user;

import com.gravityray.basiclearningsystem.user.model.LoginRequestDto;
import com.gravityray.basiclearningsystem.user.model.LoginResponseDto;
import com.gravityray.basiclearningsystem.user.model.UserDto;
import com.gravityray.basiclearningsystem.user.model.UserEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final UserConverter userConverter;

    public UserRestController(
            UserService userService,
            UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping("/me/user")
    public UserDto getMyUser() {
        long currentUserId = -1; // TODO: fetch via SpringSecurity
        UserEntity userEntity = userService.getUser(currentUserId);
        return userConverter.toUserDto(userEntity);
    }

    @GetMapping("/user/{user_id}")
    public UserDto getUser(@PathVariable("user_id") Long userId) {
        UserEntity userEntity = userService.getUser(userId);
        return userConverter.toUserDto(userEntity);
    }

    @GetMapping("/user")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(userConverter::toUserDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/user")
    public void createUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = userConverter.toUserEntity(userDto);
        userService.createUser(userEntity);
    }

    @PutMapping("/user")
    public void editUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = userConverter.toUserEntity(userDto);
        userService.updateUser(userEntity);
    }

    @DeleteMapping("/user/{user_id}")
    public void deleteUser(@PathVariable("user_id") Long userId) {
        userService.deleteUser(userId);
    }

    @DeleteMapping("/me/user")
    public void deleteMyUser() {
        long currentUserId = -1; // TODO: fetch via SpringSecurity
        userService.deleteUser(currentUserId);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        String authToken = userService.loginUser(request.getEmail(), request.getPassword());
        LoginResponseDto response = new LoginResponseDto();
        response.setAuthToken(authToken);
        return response;
    }
}

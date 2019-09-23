package com.gravityray.basiclearningsystem.user;

import com.gravityray.basiclearningsystem.user.model.UserDto;
import com.gravityray.basiclearningsystem.user.model.UserEntity;
import com.gravityray.basiclearningsystem.user.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    private final UserService userService;
    private final UserConverter userConverter;

    public UserRestController(
            @Qualifier("persistent") UserService userService,
            UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping("/me/user")
    public UserDto getMyUser(@AuthenticationPrincipal UserDetails userDetails) {
        UserEntity userEntity = userService.getUser(userDetails.getUsername());
        return userConverter.toUserDto(userEntity);
    }

    @DeleteMapping("/me/user")
    public void deleteMyUser(@AuthenticationPrincipal UserDetails userDetails) {
        long currentUserId = userService.getUser(userDetails.getUsername()).getId();
        userService.deleteUser(currentUserId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserEntity userEntity = userService.getUser(userId);
        if (userEntity == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto userDto = userConverter.toUserDto(userEntity);
        return ResponseEntity.ok(userDto);
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
}

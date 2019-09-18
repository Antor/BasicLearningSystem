package com.gravityray.basiclearningsystem.user.service;

import com.gravityray.basiclearningsystem.user.model.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();

    UserEntity getUser(long id);

    long createUser(UserEntity user);

    void updateUser(UserEntity user);

    void deleteUser(long id);

    String loginUser(String email, String password);
}

package com.gravityray.basiclearningsystem.user.service;

import com.gravityray.basiclearningsystem.user.model.Role;
import com.gravityray.basiclearningsystem.user.model.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();

    UserEntity getUser(long id);

    UserEntity getUser(String username);

    long createUser(UserEntity user);

    void updateUser(UserEntity user);

    void deleteUser(long id);

    String loginUser(String email, String password);

    public List<Role> getAllRoles();
}

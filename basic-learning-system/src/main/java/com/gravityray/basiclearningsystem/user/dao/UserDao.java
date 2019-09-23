package com.gravityray.basiclearningsystem.user.dao;

import com.gravityray.basiclearningsystem.user.model.UserEntity;

import java.util.List;

public interface UserDao {

    List<UserEntity> getAllUsers();

    UserEntity getUser(long id);

    UserEntity getUser(String username);

    long createUser(UserEntity user);

    void updateUser(UserEntity user);

    void deleteUser(long id);
}

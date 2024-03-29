package com.gravityray.basiclearningsystem.user;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();

    UserEntity getUser(long id);

    UserEntity getUser(String username);

    void createUser(UserEntity user);

    void updateUser(UserEntity user);

    void deleteUser(long id);

    String loginUser(String email, String password);

    @SuppressWarnings("UnnecessaryInterfaceModifier")
    public List<Role> getAllRoles();
}

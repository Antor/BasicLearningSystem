package com.gravityray.basiclearningsystem.user.service;

import com.gravityray.basiclearningsystem.user.dao.UserDao;
import com.gravityray.basiclearningsystem.user.model.Role;
import com.gravityray.basiclearningsystem.user.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersistentUserService implements UserService {

    private final UserDao userDao;

    public PersistentUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> result = new ArrayList<>();
        userDao.findAll().forEach(result::add);
        return result;
    }

    @Override
    public UserEntity getUser(long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public UserEntity getUser(String username) {
        return userDao.findUserByEmail(username);
    }

    @Override
    public long createUser(UserEntity user) {
        return userDao.save(user).getId();
    }

    @Override
    public void updateUser(UserEntity user) {
        userDao.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteById(id);
    }

    @Override
    public String loginUser(String email, String password) {
        // TODO: figure out
        return null;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(Role.ID_ADMIN, "Admin"));
        roleList.add(new Role(Role.ID_STUDENT, "Student"));
        return roleList;
    }
}

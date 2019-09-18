package com.gravityray.basiclearningsystem.user.service;

import com.gravityray.basiclearningsystem.user.dao.UserDao;
import com.gravityray.basiclearningsystem.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("persistent")
public class PersistentUserService implements UserService {

    private final UserDao userDao;

    public PersistentUserService(
            @Qualifier("jpa") UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public UserEntity getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    public long createUser(UserEntity user) {
        return userDao.createUser(user);
    }

    @Override
    public void updateUser(UserEntity user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public String loginUser(String email, String password) {
        // TODO: figure out
        return null;
    }
}

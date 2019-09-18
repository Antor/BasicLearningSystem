package com.gravityray.basiclearningsystem.user.service;

import com.gravityray.basiclearningsystem.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("in_memory")
public class InMemoryUserService implements UserService {

    private final Map<Long, UserEntity> userEntityByIdMap;

    private long nextId;

    public InMemoryUserService() {
        userEntityByIdMap = new HashMap<>();
        nextId = 0;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return new ArrayList<>(userEntityByIdMap.values());
    }

    @Override
    public UserEntity getUser(long id) {
        return userEntityByIdMap.get(id);
    }

    @Override
    public long createUser(UserEntity user) {
        long userId = nextId++;
        user.setId(userId);
        userEntityByIdMap.put(userId, user);
        return userId;
    }

    @Override
    public void updateUser(UserEntity user) {
        userEntityByIdMap.put(user.getId(), user);
    }

    @Override
    public void deleteUser(long id) {
        userEntityByIdMap.remove(id);
    }

    @Override
    public String loginUser(String email, String password) {
        // TODO
        return null;
    }
}

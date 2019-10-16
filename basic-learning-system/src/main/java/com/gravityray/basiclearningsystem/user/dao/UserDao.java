package com.gravityray.basiclearningsystem.user.dao;

import com.gravityray.basiclearningsystem.user.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<UserEntity, Long> {

    @Query( value = "SELECT u FROM users u WHERE u.email = :email")
    UserEntity findUserByEmail(@Param("email") String username);
}

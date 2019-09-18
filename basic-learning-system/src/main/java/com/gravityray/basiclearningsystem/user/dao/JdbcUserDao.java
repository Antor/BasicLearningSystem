package com.gravityray.basiclearningsystem.user.dao;

import com.gravityray.basiclearningsystem.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Qualifier("jdbc")
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserEntity getUser(long id) {
        return jdbcTemplate.query(
                "select id, username, password, first_name, last_name, user_role " +
                        "from users " +
                        "where id = ? ",
                new Object[]{id},
                (rs, rowNum) -> toUserEntity(rs))
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public long createUser(UserEntity user) {
        return 0;
    }

    @Override
    public void updateUser(UserEntity user) {

    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public List<UserEntity> getAllUsers() {
        return null;
    }

    private UserEntity toUserEntity(ResultSet resultSet) throws SQLException {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(resultSet.getLong("id"));
        userEntity.setEmail(resultSet.getString("username"));
        userEntity.setPassword(resultSet.getString("password"));
        userEntity.setFirstName(resultSet.getString("first_name"));
        userEntity.setLastName(resultSet.getString("last_name"));
        userEntity.setRole(resultSet.getString("user_role"));
        return userEntity;
    }

}

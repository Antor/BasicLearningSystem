package com.gravityray.basiclearningsystem.security;

import com.gravityray.basiclearningsystem.user.dao.UserDao;
import com.gravityray.basiclearningsystem.user.model.UserEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public DefaultUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("User with the username %s doesn't exist", username));
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole()));

        return userDetails;
    }
}

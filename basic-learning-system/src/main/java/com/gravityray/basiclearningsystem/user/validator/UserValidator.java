package com.gravityray.basiclearningsystem.user.validator;

import com.gravityray.basiclearningsystem.user.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    public List<String> validateUser(UserDto user) {
        List<String> errors = new ArrayList<>();

        if (user.getFirstName().trim().isEmpty()) {
            errors.add("Missing First Name");
        }

        if (user.getLastName().trim().isEmpty()) {
            errors.add("Missing Last Name");
        }

        if (user.getEmail().trim().isEmpty()) {
            errors.add("Missing Email");
        }

        if (user.getPassword().trim().isEmpty()) {
            errors.add("Missing Password");
        }

        return errors;
    }
}

package com.gravityray.basiclearningsystem.user.validator;

import com.gravityray.basiclearningsystem.user.model.dto.UserDto;
import com.gravityray.basiclearningsystem.user.model.entity.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    public List<String> validateEditUser(UserDto user) {
        List<String> errors = new ArrayList<>();

        validateFirstName(user, errors);
        validateLastName(user, errors);
        validateEmail(user, errors);
        validateRole(user, errors);

        return errors;
    }

    public List<String> validateCreateUser(UserDto user) {
        List<String> errors = new ArrayList<>();

        validateFirstName(user, errors);
        validateLastName(user, errors);
        validateEmail(user, errors);
        validatePassword(user, errors);
        validateRole(user, errors);

        return errors;
    }

    private void validateFirstName(UserDto user, List<String> errors) {
        if (user.getFirstName().trim().isEmpty()) {
            errors.add("Missing First Name");
        }
    }

    private void validateLastName(UserDto user, List<String> errors) {
        if (user.getLastName().trim().isEmpty()) {
            errors.add("Missing Last Name");
        }
    }

    private void validateEmail(UserDto user, List<String> errors) {
        if (user.getEmail().trim().isEmpty()) {
            errors.add("Missing Email");
        }
    }

    private void validatePassword(UserDto user, List<String> errors) {
        if (user.getPassword().trim().isEmpty()) {
            errors.add("Missing Password");
        }
    }

    private void validateRole(UserDto user, List<String> errors) {
        String role = user.getRole().trim();
        if (role.isEmpty()) {
            errors.add("Missing Role");
        }
        if (!Role.ID_ADMIN.equals(role)
                && !Role.ID_STUDENT.equals(role)) {
            errors.add("Unknown Role: " + role);
        }
    }

}

package com.gravityray.basiclearningsystem.user.profile;

import com.gravityray.basiclearningsystem.user.UserEntity;
import com.gravityray.basiclearningsystem.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultProfileService implements ProfileService {

    private final UserService userService;
    private final Validator validator;

    public DefaultProfileService(UserService userService, Validator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @Override
    public ViewProfileInfo getViewProfileInfo(String email) throws ProfileNotFoundException {
        UserEntity user = userService.getUser(email);

        if (user == null) {
            throw new ProfileNotFoundException();
        }

        ViewProfileInfo profileInfo = new ViewProfileInfo();
        profileInfo.setName(String.format("%s %s", user.getFirstName(), user.getLastName()));
        profileInfo.setEmail(user.getEmail());
        return profileInfo;
    }

    @Override
    public EditProfileForm getEditProfileForm(String email) throws ProfileNotFoundException {
        UserEntity user = userService.getUser(email);

        if (user == null) {
            throw new ProfileNotFoundException();
        }

        EditProfileForm profile = new EditProfileForm();
        profile.setId(user.getId());
        profile.setFirstName(user.getFirstName());
        profile.setLastName(user.getLastName());

        return profile;
    }

    @Override
    @Transactional
    public void updateProfile(EditProfileForm profile)
            throws ProfileNotFoundException, EditProfileFormNotValidException {
        Set<ConstraintViolation<EditProfileForm>> constraintViolationSet = validator.validate(profile);
        if (!constraintViolationSet.isEmpty()) {
            throw new EditProfileFormNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }

        UserEntity user = userService.getUser(profile.getId());
        if (user == null) {
            throw new ProfileNotFoundException();
        }
        user.setFirstName(profile.getFirstName());
        user.setLastName(profile.getLastName());
    }

    @Override
    public DeleteProfileInfo getDeleteProfileInfo(String email) throws ProfileNotFoundException {
        UserEntity user = userService.getUser(email);

        if (user == null) {
            throw new ProfileNotFoundException();
        }

        DeleteProfileInfo profileInfo = new DeleteProfileInfo();
        profileInfo.setId(user.getId());
        return profileInfo;
    }

    @Override
    @Transactional
    public void deleteProfile(DeleteProfileInfo profileInfo) {
        userService.deleteUser(profileInfo.getId());
    }
}

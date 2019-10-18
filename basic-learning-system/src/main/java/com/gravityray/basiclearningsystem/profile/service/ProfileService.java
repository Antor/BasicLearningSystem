package com.gravityray.basiclearningsystem.profile.service;

import com.gravityray.basiclearningsystem.profile.model.DeleteProfileInfo;
import com.gravityray.basiclearningsystem.profile.model.EditProfileForm;
import com.gravityray.basiclearningsystem.profile.model.ViewProfileInfo;

public interface ProfileService {

    ViewProfileInfo getViewProfileInfo(String email) throws ProfileNotFoundException;

    EditProfileForm getEditProfileForm(String email) throws ProfileNotFoundException;

    void updateProfile(EditProfileForm profile)
            throws ProfileNotFoundException, EditProfileFormNotValidException;

    DeleteProfileInfo getDeleteProfileInfo(String email) throws ProfileNotFoundException;

    void deleteProfile(DeleteProfileInfo profileInfo);
}

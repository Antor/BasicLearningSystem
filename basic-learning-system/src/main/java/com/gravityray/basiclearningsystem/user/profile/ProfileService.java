package com.gravityray.basiclearningsystem.user.profile;

public interface ProfileService {

    ViewProfileInfo getViewProfileInfo(String email) throws ProfileNotFoundException;

    EditProfileForm getEditProfileForm(String email) throws ProfileNotFoundException;

    void updateProfile(EditProfileForm profile)
            throws ProfileNotFoundException, EditProfileFormNotValidException;

    DeleteProfileInfo getDeleteProfileInfo(String email) throws ProfileNotFoundException;

    void deleteProfile(DeleteProfileInfo profileInfo);

    ProfileCourseListInfo getProfileCourseListInfo(String username);
}

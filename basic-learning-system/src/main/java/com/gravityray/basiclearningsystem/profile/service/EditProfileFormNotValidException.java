package com.gravityray.basiclearningsystem.profile.service;

import java.util.List;

public class EditProfileFormNotValidException extends Exception {

    private final List<String> errorMessageList;

    @SuppressWarnings("WeakerAccess")
    public EditProfileFormNotValidException(List<String> errorMessageList) {
        this.errorMessageList = errorMessageList;
    }

    public List<String> getErrorMessageList() {
        return errorMessageList;
    }
}

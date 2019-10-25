package com.gravityray.basiclearningsystem.adminpanel.course;

import java.util.List;

public class UnitCreateInfoNotValidException extends Throwable {

    private List<String> errorList;

    @SuppressWarnings("WeakerAccess")
    public UnitCreateInfoNotValidException(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

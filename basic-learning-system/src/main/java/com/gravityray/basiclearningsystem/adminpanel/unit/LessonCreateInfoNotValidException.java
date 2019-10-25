package com.gravityray.basiclearningsystem.adminpanel.unit;

import java.util.List;

public class LessonCreateInfoNotValidException extends Throwable {

    private List<String> errorList;

    @SuppressWarnings("WeakerAccess")
    public LessonCreateInfoNotValidException(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

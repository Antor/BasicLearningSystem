package com.gravityray.basiclearningsystem.adminpanel.course;

import java.util.List;

public class CourseCreateInfoNotValidException extends Throwable {

    private final List<String> errorList;

    @SuppressWarnings("WeakerAccess")
    public CourseCreateInfoNotValidException(
            List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

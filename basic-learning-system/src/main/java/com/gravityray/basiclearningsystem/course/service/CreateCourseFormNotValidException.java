package com.gravityray.basiclearningsystem.course.service;

import java.util.List;

public class CreateCourseFormNotValidException extends Throwable {

    private List<String> errorList;

    public CreateCourseFormNotValidException(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

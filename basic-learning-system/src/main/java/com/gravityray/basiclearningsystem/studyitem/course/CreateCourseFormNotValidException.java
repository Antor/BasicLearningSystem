package com.gravityray.basiclearningsystem.studyitem.course;

import java.util.List;

public class CreateCourseFormNotValidException extends Throwable {

    private List<String> errorList;

    @SuppressWarnings("WeakerAccess")
    public CreateCourseFormNotValidException(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

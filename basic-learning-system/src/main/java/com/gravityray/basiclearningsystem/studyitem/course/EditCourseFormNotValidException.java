package com.gravityray.basiclearningsystem.studyitem.course;

import java.util.List;

public class EditCourseFormNotValidException extends Throwable {

    private List<String> errorList;

    @SuppressWarnings("WeakerAccess")
    public EditCourseFormNotValidException(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

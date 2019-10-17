package com.gravityray.basiclearningsystem.lessonitem.service;

import java.util.List;

public class CreateLessonItemFormNotValidException extends Throwable {

    private List<String> errorList;

    @SuppressWarnings("WeakerAccess")
    public CreateLessonItemFormNotValidException(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

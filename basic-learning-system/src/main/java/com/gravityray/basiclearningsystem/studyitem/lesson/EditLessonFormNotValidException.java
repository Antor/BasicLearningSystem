package com.gravityray.basiclearningsystem.studyitem.lesson;

import java.util.List;

public class EditLessonFormNotValidException extends Throwable {

    private List<String> errorList;

    @SuppressWarnings("WeakerAccess")
    public EditLessonFormNotValidException(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

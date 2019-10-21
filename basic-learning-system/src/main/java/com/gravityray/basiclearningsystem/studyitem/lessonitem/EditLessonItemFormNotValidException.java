package com.gravityray.basiclearningsystem.studyitem.lessonitem;

import java.util.List;

public class EditLessonItemFormNotValidException extends Throwable {

    private List<String> errorList;

    @SuppressWarnings("WeakerAccess")
    public EditLessonItemFormNotValidException(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

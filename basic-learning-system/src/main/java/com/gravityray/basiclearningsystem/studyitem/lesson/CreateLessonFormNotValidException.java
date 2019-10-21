package com.gravityray.basiclearningsystem.studyitem.lesson;

import java.util.List;

public class CreateLessonFormNotValidException extends Throwable {

    private List<String> errorList;

    @SuppressWarnings("WeakerAccess")
    public CreateLessonFormNotValidException(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

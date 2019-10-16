package com.gravityray.basiclearningsystem.unit.service;

import java.util.List;

public class CreateUnitFormNotValidException extends Throwable {

    private List<String> errorList;

    @SuppressWarnings("WeakerAccess")
    public CreateUnitFormNotValidException(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

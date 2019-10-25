package com.gravityray.basiclearningsystem.adminpanel.unit;

import java.util.List;

public class UnitEditInfoNotValidException extends Throwable {

    private List<String> errorList;

    @SuppressWarnings("WeakerAccess")
    public UnitEditInfoNotValidException(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

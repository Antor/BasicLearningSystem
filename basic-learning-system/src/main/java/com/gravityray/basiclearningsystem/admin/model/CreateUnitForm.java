package com.gravityray.basiclearningsystem.admin.model;

import javax.validation.constraints.NotBlank;

public class CreateUnitForm {

    @NotBlank
    private String title;

    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

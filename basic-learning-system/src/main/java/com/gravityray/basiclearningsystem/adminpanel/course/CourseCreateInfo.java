package com.gravityray.basiclearningsystem.adminpanel.course;

import javax.validation.constraints.NotBlank;

public class CourseCreateInfo {

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

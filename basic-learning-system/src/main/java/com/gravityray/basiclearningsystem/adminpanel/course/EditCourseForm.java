package com.gravityray.basiclearningsystem.adminpanel.course;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EditCourseForm {

    @NotNull
    private Long id;

    @NotBlank
    private String title;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

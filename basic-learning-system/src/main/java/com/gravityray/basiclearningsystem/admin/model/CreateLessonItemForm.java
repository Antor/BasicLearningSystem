package com.gravityray.basiclearningsystem.admin.model;

import javax.validation.constraints.NotBlank;

public class CreateLessonItemForm {

    @NotBlank
    private String title;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

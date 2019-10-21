package com.gravityray.basiclearningsystem.adminpanel.lessonitem;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EditLessonItemForm {

    @NotNull
    private Long id;

    @NotBlank
    private String title;

    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

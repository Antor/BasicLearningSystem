package com.gravityray.basiclearningsystem.course.model;

import java.util.List;

public class LessonDto {

    private Long id;
    private String title;
    private String description;
    private Integer ordinal;
    private Long unitId;

    private List<LessonItemDto> lessonItems;

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

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public List<LessonItemDto> getLessonItems() {
        return lessonItems;
    }

    public void setLessonItems(List<LessonItemDto> lessonItems) {
        this.lessonItems = lessonItems;
    }
}

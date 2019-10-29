package com.gravityray.basiclearningsystem.study;

import java.util.List;

public class CourseTreeLesson {
    private long id;
    private String title;
    private List<CourseTreeLessonItem> lessonItemList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CourseTreeLessonItem> getLessonItemList() {
        return lessonItemList;
    }

    public void setLessonItemList(List<CourseTreeLessonItem> lessonItemList) {
        this.lessonItemList = lessonItemList;
    }
}

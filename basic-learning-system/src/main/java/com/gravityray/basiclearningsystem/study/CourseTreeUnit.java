package com.gravityray.basiclearningsystem.study;

import java.util.List;

public class CourseTreeUnit {
    private long id;
    private String title;
    private List<CourseTreeLesson> lessonList;

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

    public List<CourseTreeLesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<CourseTreeLesson> lessonList) {
        this.lessonList = lessonList;
    }
}

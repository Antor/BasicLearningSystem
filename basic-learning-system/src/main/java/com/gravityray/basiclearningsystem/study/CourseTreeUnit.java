package com.gravityray.basiclearningsystem.study;

import java.util.List;

public class CourseTreeUnit {
    private long id;
    private String title;
    private List<CourseTreeLesson> lessonList;

    private long lessonItemCountCompleted;
    private long lessonItemCountTotal;

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

    public long getLessonItemCountCompleted() {
        return lessonItemCountCompleted;
    }

    public void setLessonItemCountCompleted(long lessonItemCountCompleted) {
        this.lessonItemCountCompleted = lessonItemCountCompleted;
    }

    public long getLessonItemCountTotal() {
        return lessonItemCountTotal;
    }

    public void setLessonItemCountTotal(long lessonItemCountTotal) {
        this.lessonItemCountTotal = lessonItemCountTotal;
    }

    public double getCompletionPercent() {
        return lessonItemCountTotal == 0
                ? 1
                : ((double) lessonItemCountCompleted) / lessonItemCountTotal;
    }
}

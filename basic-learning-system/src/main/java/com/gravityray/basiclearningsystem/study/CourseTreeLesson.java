package com.gravityray.basiclearningsystem.study;

import java.util.List;

public class CourseTreeLesson {
    private long id;
    private String title;
    private List<CourseTreeLessonItem> lessonItemList;

    private long lessonItemCountCompleted;
    private long lessonItemCountTotal;

    private boolean currentStudyItem = false;

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

    public boolean isCurrentStudyItem() {
        return currentStudyItem;
    }

    public void setCurrentStudyItem(boolean currentStudyItem) {
        this.currentStudyItem = currentStudyItem;
    }
}

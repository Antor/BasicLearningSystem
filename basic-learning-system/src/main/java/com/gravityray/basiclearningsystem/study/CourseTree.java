package com.gravityray.basiclearningsystem.study;

import java.util.List;

public class CourseTree {
    private long id;
    private String title;
    private List<CourseTreeUnit> unitList;

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

    public List<CourseTreeUnit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<CourseTreeUnit> unitList) {
        this.unitList = unitList;
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

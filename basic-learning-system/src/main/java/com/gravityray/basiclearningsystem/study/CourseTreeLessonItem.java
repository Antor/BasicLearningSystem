package com.gravityray.basiclearningsystem.study;

public class CourseTreeLessonItem {
    private long id;
    private String title;
    private boolean complete;

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

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isCurrentStudyItem() {
        return currentStudyItem;
    }

    public void setCurrentStudyItem(boolean currentStudyItem) {
        this.currentStudyItem = currentStudyItem;
    }
}

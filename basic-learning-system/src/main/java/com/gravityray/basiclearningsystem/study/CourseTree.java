package com.gravityray.basiclearningsystem.study;

import java.util.List;

public class CourseTree {
    private long id;
    private String title;
    private List<CourseTreeUnit> unitList;

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
}

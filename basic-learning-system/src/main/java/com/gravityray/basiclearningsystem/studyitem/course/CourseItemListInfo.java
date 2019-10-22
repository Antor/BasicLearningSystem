package com.gravityray.basiclearningsystem.studyitem.course;

import java.util.List;

public class CourseItemListInfo {

    private List<CourseItemInfo> courseList;

    private boolean userAuthenticated;

    public List<CourseItemInfo> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseItemInfo> courseList) {
        this.courseList = courseList;
    }

    public boolean isUserAuthenticated() {
        return userAuthenticated;
    }

    public void setUserAuthenticated(boolean userAuthenticated) {
        this.userAuthenticated = userAuthenticated;
    }
}

package com.gravityray.basiclearningsystem.catalogue;

import java.util.List;

public class CourseCatalogue {

    private List<CourseCatalogueItem> courseList;

    private boolean userAuthenticated;

    public List<CourseCatalogueItem> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseCatalogueItem> courseList) {
        this.courseList = courseList;
    }

    public boolean isUserAuthenticated() {
        return userAuthenticated;
    }

    public void setUserAuthenticated(boolean userAuthenticated) {
        this.userAuthenticated = userAuthenticated;
    }
}

package com.gravityray.basiclearningsystem.adminpanel.course;

import java.util.List;

public interface AdminPanelCourseService {

    List<CourseListItemInfo> getCourseList();

    void createCourse(CourseCreateInfo course)
            throws CourseCreateInfoNotValidException;

    CourseEditInfo getCourseEditInfo(long courseId);

    void updateCourse(CourseEditInfo courseEditInfo)
            throws CourseNotFoundException, CourseEditInfoNotValidException;

    CourseActivateInfo getCourseActivateInfo(long courseId);

    CourseDeactivateInfo getCourseDeactivateInfo(long courseId);

    void activateCourse(long courseId);

    void deactivateCourse(long courseId);

    CourseDeleteInfo getCourseDeleteInfo(long courseId);

    void deleteCourse(long id);

    UnitListCourseInfo getUnitListCourseInfo(long courseId);

    List<UnitListItemInfo> getUnitList(long courseId);

    UnitCreateCourseInfo getUnitCreateCourseInfo(long courseId);

    void createUnit(long courseId, UnitCreateInfo unitCreateInfo)
            throws UnitCreateInfoNotValidException;
}

package com.gravityray.basiclearningsystem.course.service;

import com.gravityray.basiclearningsystem.course.model.CourseEntity;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;

import java.util.List;

public class PersistentCourseService implements CourseService {
    @Override
    public List<CourseEntity> getAllCourses() {
        return null;
    }

    @Override
    public List<CourseEntity> getActiveCourses() {
        return null;
    }

    @Override
    public CourseEntity getCourse(long id) {
        return null;
    }

    @Override
    public long addCourse(CourseEntity courseEntity) {
        return 0;
    }

    @Override
    public void updateCourse(CourseEntity courseEntity) {

    }

    @Override
    public void activateCourse(long courseId) {

    }

    @Override
    public void deactivateCourse(long courseId) {

    }

    @Override
    public void deleteCourse(long id) {

    }

    @Override
    public List<UnitEntity> getCourseUnits(long courseId) {
        return null;
    }

    @Override
    public List<CourseEntity> getUserCourses(long userId) {
        return null;
    }

    @Override
    public void enrollUserToCourse(long userId, long courseId) {

    }

    @Override
    public void unenrollUserFromCourse(long userId, long courseId) {

    }
}

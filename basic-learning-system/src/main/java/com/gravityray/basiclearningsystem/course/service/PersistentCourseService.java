package com.gravityray.basiclearningsystem.course.service;

import com.gravityray.basiclearningsystem.course.dao.CourseDao;
import com.gravityray.basiclearningsystem.course.model.CourseEntity;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentCourseService implements CourseService {

    private final CourseDao courseDao;

    public PersistentCourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<CourseEntity> getCourses(boolean onlyActive) {
        return courseDao.getCourses(onlyActive);
    }

    @Override
    public CourseEntity getCourse(long id) {
        return courseDao.getCourse(id);
    }

    @Override
    public long addCourse(CourseEntity courseEntity) {
        return courseDao.addCourse(courseEntity);
    }

    @Override
    public void updateCourse(CourseEntity courseEntity) {
        courseDao.updateCourse(courseEntity);
    }

    @Override
    public void activateCourse(long courseId) {
        courseDao.activateCourse(courseId);
    }

    @Override
    public void deactivateCourse(long courseId) {
        courseDao.deactivateCourse(courseId);
    }

    @Override
    public void deleteCourse(long id) {
        courseDao.deleteCourse(id);
    }

    @Override
    public List<UnitEntity> getCourseUnits(long courseId) {
        return courseDao.getCourseUnits(courseId);
    }

    @Override
    public List<CourseEntity> getUserCourses(long userId) {
        return courseDao.getUserCourses(userId);
    }

    @Override
    public void enrollUserToCourse(long userId, long courseId) {
        courseDao.enrollUserToCourse(userId, courseId);
    }

    @Override
    public void unenrollUserFromCourse(long userId, long courseId) {
        courseDao.unenrollUserFromCourse(userId, courseId);
    }
}

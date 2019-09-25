package com.gravityray.basiclearningsystem.course.service;

import com.gravityray.basiclearningsystem.course.dao.CourseDao;
import com.gravityray.basiclearningsystem.course.model.CourseEntity;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersistentCourseService implements CourseService {

    private final CourseDao courseDao;

    public PersistentCourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<CourseEntity> getCourses(boolean onlyActive) {
        Iterable<CourseEntity> courseIterable = onlyActive
                ? courseDao.getActiveCourses()
                : courseDao.findAll();

        List<CourseEntity> courseList = new ArrayList<>();
        courseIterable.forEach(courseList::add);
        return courseList;
    }

    @Override
    public CourseEntity getCourse(long id) {
        return courseDao.findById(id).orElse(null);
    }

    @Override
    public long addCourse(CourseEntity courseEntity) {
        return courseDao.save(courseEntity).getId();
    }

    @Override
    public void updateCourse(CourseEntity courseEntity) {
        courseDao.save(courseEntity);
    }

    @Override
    public void activateCourse(long courseId) {
        courseDao.findById(courseId)
                .orElse(null)
                .setActive(true);
    }

    @Override
    public void deactivateCourse(long courseId) {
        courseDao.findById(courseId)
                .orElse(null)
                .setActive(false);
    }

    @Override
    public void deleteCourse(long id) {
        courseDao.deleteById(id);
    }

    @Override
    public List<UnitEntity> getCourseUnits(long courseId) {
        // TODO
        return new ArrayList<>();
    }

    @Override
    public List<CourseEntity> getUserCourses(long userId) {
        // TODO
        return new ArrayList<>();
    }

    @Override
    public void enrollUserToCourse(long userId, long courseId) {
        // TODO
    }

    @Override
    public void unenrollUserFromCourse(long userId, long courseId) {
        // TODO
    }
}

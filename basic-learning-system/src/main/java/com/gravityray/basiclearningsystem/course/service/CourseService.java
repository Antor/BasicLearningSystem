package com.gravityray.basiclearningsystem.course.service;

import com.gravityray.basiclearningsystem.course.model.CourseEntity;
import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;

import java.util.List;

public interface CourseService {

    List<CourseEntity> getCourses(boolean onlyActive);

    CourseEntity getCourse(long id);

    long addCourse(CourseEntity courseEntity);

    void updateCourse(CourseEntity courseEntity);

    void activateCourse(long courseId);

    void deactivateCourse(long courseId);

    void deleteCourse(long id);

    List<UnitEntity> getCourseUnits(long courseId);


    List<CourseEntity> getUserCourses(long userId);

    void enrollUserToCourse(long userId, long courseId);

    void unenrollUserFromCourse(long userId, long courseId);
}

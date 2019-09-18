package com.gravityray.basiclearningsystem.course.service;

import com.gravityray.basiclearningsystem.course.model.CourseEntity;
import com.gravityray.basiclearningsystem.course.model.LessonEntity;
import com.gravityray.basiclearningsystem.course.model.LessonItemEntity;
import com.gravityray.basiclearningsystem.course.model.UnitEntity;

import java.util.List;

public interface CourseService {
    List<CourseEntity> getUserCourses(long userId);

    void enrollUserToCourse(long userId, long courseId);

    void unenrollUserFromCourse(long userId, long courseId);

    List<CourseEntity> getAllCourses();

    List<CourseEntity> getActiveCourses();

    CourseEntity getCourse(long id);

    long addCourse(CourseEntity courseEntity);

    void updateCourse(CourseEntity courseEntity);

    void activateCourse(long courseId);

    void deactivateCourse(long courseId);

    void deleteCourse(long id);

    List<UnitEntity> getCourseUnits(long courseId);

    UnitEntity getUnit(long id);

    long addUnit(UnitEntity unitEntity);

    void updateUnit(UnitEntity unitEntity);

    void changeUnitOrdinal(long unitId, int delta);

    void deleteUnit(long id);

    List<LessonEntity> getUnitLessons(long unitId);

    LessonEntity getLesson(long id);

    long addLesson(LessonEntity lessonEntity);

    void updateLesson(LessonEntity lessonEntity);

    void changeLessonOrdinal(long lessonId, int delta);

    void decreaseLessonOrdinal(long lessonId);

    void deleteLesson(long id);

    List<LessonItemEntity> getLessonLessonItems(long lessonId);

    LessonItemEntity getLessonItem(long id);

    long addLessonItem(LessonItemEntity lessonItemEntity);

    void updateLessonItem(LessonItemEntity lessonItemEntity);

    void changeLessonItemOrdinal(long lessonItemId, int delta);

    void deleteLessonItem(long id);

    void completeLessonItem(long userId, long lessonItemId);
}

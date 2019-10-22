package com.gravityray.basiclearningsystem.studyitem.course;

import com.gravityray.basiclearningsystem.adminpanel.course.CourseActiveToggleInfo;
import com.gravityray.basiclearningsystem.adminpanel.course.CreateCourseForm;
import com.gravityray.basiclearningsystem.adminpanel.course.DeleteCourseInfo;
import com.gravityray.basiclearningsystem.adminpanel.course.EditCourseForm;
import com.gravityray.basiclearningsystem.studyitem.unit.UnitEntity;

import java.util.List;

@SuppressWarnings("unused")
public interface CourseService {

    List<CourseEntity> getCourses(boolean onlyActive);

    CourseEntity getCourse(long id);

    EditCourseForm getEditCourseForm(long id);

    DeleteCourseInfo getDeleteCourseInfo(long id);

    CourseActiveToggleInfo getCourseActiveToggleInfo(Long courseId);

    void createCourse(CreateCourseForm course) throws CreateCourseFormNotValidException;

    long addCourse(CourseEntity courseEntity);

    void updateCourse(CourseEntity courseEntity);

    void updateCourse(EditCourseForm editCourseForm) throws CourseNotFoundException, EditCourseFormNotValidException;

    void toggleCourseActive(Long courseId);

    void activateCourse(long courseId);

    void deactivateCourse(long courseId);

    void deleteCourse(long id);

    List<UnitEntity> getCourseUnits(long courseId);

    List<CourseEntity> getUserCourses(long userId);

    void enrollUserToCourse(long userId, long courseId);

    void unenrollUserFromCourse(long userId, long courseId);

    CourseItemListInfo getActiveCourseItemListInfo(String email);
}

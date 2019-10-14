package com.gravityray.basiclearningsystem.course.service;

import com.gravityray.basiclearningsystem.admin.controller.ui.CourseActiveToggleInfo;
import com.gravityray.basiclearningsystem.admin.controller.ui.CreateCourseForm;
import com.gravityray.basiclearningsystem.admin.controller.ui.DeleteCourseInfo;
import com.gravityray.basiclearningsystem.admin.controller.ui.EditCourseForm;
import com.gravityray.basiclearningsystem.course.model.entity.CourseEntity;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}

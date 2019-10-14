package com.gravityray.basiclearningsystem.course.service;

import com.gravityray.basiclearningsystem.admin.controller.ui.CreateCourseForm;
import com.gravityray.basiclearningsystem.admin.controller.ui.EditCourseForm;
import com.gravityray.basiclearningsystem.course.dao.CourseDao;
import com.gravityray.basiclearningsystem.course.model.entity.CourseEntity;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultCourseService implements CourseService {

    private final CourseDao courseDao;
    private final Validator validator;

    public DefaultCourseService(
            CourseDao courseDao,
            Validator validator) {
        this.courseDao = courseDao;
        this.validator = validator;
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
    public EditCourseForm getEditCourseForm(long id) {
        return courseDao.findById(id)
                .map(courseEntity -> {
                    EditCourseForm form = new EditCourseForm();
                    form.setId(courseEntity.getId());
                    form.setTitle(courseEntity.getTitle());
                    form.setDescription(courseEntity.getDescription());
                    return form;
                })
                .orElse(null);
    }

    @Transactional
    @Override
    public void createCourse(CreateCourseForm course)
            throws CreateCourseFormNotValidException {
        Set<ConstraintViolation<CreateCourseForm>> constraintViolationSet =
                validator.validate(course);
        if (!constraintViolationSet.isEmpty()) {
            throw new CreateCourseFormNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setTitle(course.getTitle());
        courseEntity.setDescription(course.getDescription());
        courseEntity.setActive(false);
        courseDao.save(courseEntity);
    }

    @Override
    public long addCourse(CourseEntity courseEntity) {
        return courseDao.save(courseEntity).getId();
    }

    @Override
    public void updateCourse(CourseEntity courseEntity) {
        courseDao.save(courseEntity);
    }

    @Transactional
    @Override
    public void updateCourse(EditCourseForm editCourseForm)
            throws CourseNotFoundException, EditCourseFormNotValidException {
        Set<ConstraintViolation<EditCourseForm>> constraintViolationSet =
                validator.validate(editCourseForm);
        if (!constraintViolationSet.isEmpty()) {
            throw new EditCourseFormNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }

        CourseEntity courseEntity = courseDao.findById(editCourseForm.getId())
                .orElse(null);
        if (courseEntity == null) {
            throw new CourseNotFoundException();
        }
        courseEntity.setTitle(editCourseForm.getTitle());
        courseEntity.setDescription(editCourseForm.getDescription());
    }

    @Override
    public void activateCourse(long courseId) {
        courseDao.findById(courseId)
                .ifPresent(courseEntity -> courseEntity.setActive(true));
    }

    @Override
    public void deactivateCourse(long courseId) {
        courseDao.findById(courseId)
                .ifPresent(courseEntity -> courseEntity.setActive(true));
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

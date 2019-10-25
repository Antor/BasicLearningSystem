package com.gravityray.basiclearningsystem.adminpanel.course;

import com.gravityray.basiclearningsystem.studyitem.course.CourseDao;
import com.gravityray.basiclearningsystem.studyitem.course.CourseEntity;
import com.gravityray.basiclearningsystem.studyitem.unit.UnitDao;
import com.gravityray.basiclearningsystem.studyitem.unit.UnitEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultAdminPanelCourseService implements AdminPanelCourseService {

    private final CourseDao courseDao;
    private final UnitDao unitDao;

    private final Validator validator;

    public DefaultAdminPanelCourseService(
            CourseDao courseDao,
            UnitDao unitDao,
            Validator validator) {
        this.courseDao = courseDao;
        this.unitDao = unitDao;
        this.validator = validator;
    }

    @Override
    public List<CourseListItemInfo> getCourseList() {
        List<CourseListItemInfo> courseList = new ArrayList<>();
        courseDao.findAll()
                .forEach(courseEntity -> {
                    CourseListItemInfo course = new CourseListItemInfo();
                    course.setId(courseEntity.getId());
                    course.setTitle(courseEntity.getTitle());
                    course.setDescription(courseEntity.getDescription());
                    course.setActive(courseEntity.isActive());
                    courseList.add(course);
                });
        return courseList;
    }

    @Transactional
    @Override
    public void createCourse(CourseCreateInfo form)
            throws CourseCreateInfoNotValidException {
        Set<ConstraintViolation<CourseCreateInfo>> constraintViolationSet =
                validator.validate(form);
        if (!constraintViolationSet.isEmpty()) {
            throw new CourseCreateInfoNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setTitle(form.getTitle());
        courseEntity.setDescription(form.getDescription());
        courseEntity.setActive(false);
        courseDao.save(courseEntity);
    }

    @Override
    public CourseEditInfo getCourseEditInfo(long courseId) {
        return courseDao.findById(courseId)
                .map(courseEntity -> {
                    CourseEditInfo course = new CourseEditInfo();
                    course.setId(courseEntity.getId());
                    course.setTitle(courseEntity.getTitle());
                    course.setDescription(courseEntity.getDescription());
                    return course;
                })
                .orElse(null);
    }

    @Transactional
    @Override
    public void updateCourse(CourseEditInfo courseEditInfo)
            throws CourseNotFoundException, CourseEditInfoNotValidException {
        Set<ConstraintViolation<CourseEditInfo>> constraintViolationSet =
                validator.validate(courseEditInfo);
        if (!constraintViolationSet.isEmpty()) {
            throw new CourseEditInfoNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }

        CourseEntity courseEntity = courseDao.findById(courseEditInfo.getId())
                .orElse(null);
        if (courseEntity == null) {
            throw new CourseNotFoundException();
        }
        courseEntity.setTitle(courseEditInfo.getTitle());
        courseEntity.setDescription(courseEditInfo.getDescription());
    }

    @Override
    public CourseDeleteInfo getCourseDeleteInfo(long id) {
        return courseDao.findById(id)
                .map(courseEntity -> {
                    CourseDeleteInfo course = new CourseDeleteInfo();
                    course.setId(courseEntity.getId());
                    course.setTitle(courseEntity.getTitle());
                    return course;
                })
                .orElse(null);
    }

    @Transactional
    @Override
    public void deleteCourse(long id) {
        courseDao.deleteById(id);
    }

    @Override
    public CourseActivateInfo getCourseActivateInfo(long id) {
        return courseDao.findById(id)
                .map(courseEntity -> {
                    CourseActivateInfo course = new CourseActivateInfo();
                    course.setId(courseEntity.getId());
                    course.setTitle(courseEntity.getTitle());
                    return course;
                })
                .orElse(null);
    }

    @Override
    public CourseDeactivateInfo getCourseDeactivateInfo(long id) {
        return courseDao.findById(id)
                .map(courseEntity -> {
                    CourseDeactivateInfo course = new CourseDeactivateInfo();
                    course.setId(courseEntity.getId());
                    course.setTitle(courseEntity.getTitle());
                    return course;
                })
                .orElse(null);
    }

    @Transactional
    @Override
    public void activateCourse(long id) {
        courseDao.findById(id)
                .ifPresent(course -> course.setActive(true));
    }

    @Transactional
    @Override
    public void deactivateCourse(long id) {
        courseDao.findById(id)
                .ifPresent(course -> course.setActive(false));
    }

    @Override
    public UnitListCourseInfo getUnitListCourseInfo(long courseId) {
        return courseDao.findById(courseId)
                .map(courseEntity -> {
                    UnitListCourseInfo course = new UnitListCourseInfo();
                    course.setId(courseEntity.getId());
                    course.setTitle(courseEntity.getTitle());
                    return course;
                })
                .orElse(null);
    }

    @Override
    public List<UnitListItemInfo> getUnitList(long courseId) {
        return courseDao.findById(courseId)
                .map(courseEntity -> {
                    return courseEntity.getUnitList()
                            .stream()
                            .map(unitEntity -> {
                                UnitListItemInfo item = new UnitListItemInfo();
                                item.setId(unitEntity.getId());
                                item.setTitle(unitEntity.getTitle());
                                item.setDescription(unitEntity.getDescription());
                                return item;
                            })
                            .collect(Collectors.toList());
                })
                .orElse(new ArrayList<>());
    }

    @Override
    public UnitCreateCourseInfo getUnitCreateCourseInfo(long courseId) {
        return courseDao.findById(courseId)
                .map(courseEntity -> {
                    UnitCreateCourseInfo course = new UnitCreateCourseInfo();
                    course.setId(courseEntity.getId());
                    course.setTitle(courseEntity.getTitle());
                    return course;
                })
                .orElse(null);
    }

    @Transactional
    @Override
    public void createUnit(long courseId, UnitCreateInfo unitCreateInfo)
            throws UnitCreateInfoNotValidException {
        Set<ConstraintViolation<UnitCreateInfo>> constraintViolationSet =
                validator.validate(unitCreateInfo);
        if (!constraintViolationSet.isEmpty()) {
            throw new UnitCreateInfoNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }

        UnitEntity unitEntity = new UnitEntity();
        unitEntity.setTitle(unitCreateInfo.getTitle());
        unitEntity.setDescription(unitCreateInfo.getDescription());
        unitEntity.setOrdinal(0);// TODO: set proper value
        unitEntity.setCourseId(courseId);

        unitDao.save(unitEntity);
    }
}

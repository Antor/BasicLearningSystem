package com.gravityray.basiclearningsystem.adminpanel.unit;

import com.gravityray.basiclearningsystem.studyitem.lesson.LessonDao;
import com.gravityray.basiclearningsystem.studyitem.lesson.LessonEntity;
import com.gravityray.basiclearningsystem.studyitem.unit.UnitDao;
import com.gravityray.basiclearningsystem.studyitem.unit.UnitEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultAdminPanelUnitService implements AdminPanelUnitService {

    private final UnitDao unitDao;
    private final LessonDao lessonDao;

    private final Validator validator;

    public DefaultAdminPanelUnitService(
            UnitDao unitDao,
            LessonDao lessonDao,
            Validator validator) {
        this.unitDao = unitDao;
        this.lessonDao = lessonDao;
        this.validator = validator;
    }

    @Override
    public UnitEditCourseInfo getUnitEditCourseInfo(long unitId) {
        return unitDao.findById(unitId)
                .map(UnitEntity::getCourse)
                .map(courseEntity -> {
                    UnitEditCourseInfo course = new UnitEditCourseInfo();
                    course.setTitle(courseEntity.getTitle());
                    return course;
                })
                .orElse(null);
    }

    @Override
    public UnitEditInfo getUnitEditInfo(long unitId) {
        return unitDao.findById(unitId)
                .map(unitEntity -> {
                    UnitEditInfo unit = new UnitEditInfo();
                    unit.setTitle(unitEntity.getTitle());
                    unit.setDescription(unitEntity.getDescription());
                    return unit;
                })
                .orElse(null);
    }

    @Transactional
    @Override
    public void updateUnit(long unitId, UnitEditInfo unitEditInfo)
            throws UnitEditInfoNotValidException {
        Set<ConstraintViolation<UnitEditInfo>> constraintViolationSet =
                validator.validate(unitEditInfo);
        if (!constraintViolationSet.isEmpty()) {
            throw new UnitEditInfoNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }

        unitDao.findById(unitId)
                .ifPresent(unitEntity -> {
                    unitEntity.setTitle(unitEditInfo.getTitle());
                    unitEntity.setDescription(unitEditInfo.getDescription());
                });
    }

    @Override
    public long getUnitCourseId(long unitId) {
        return unitDao.findById(unitId)
                .orElse(null)
                .getCourseId();
    }

    @Override
    public UnitDeleteCourseInfo getUnitDeleteCourseInfo(long unitId) {
        return unitDao.findById(unitId)
                .map(UnitEntity::getCourse)
                .map(courseEntity -> {
                    UnitDeleteCourseInfo course = new UnitDeleteCourseInfo();
                    course.setTitle(courseEntity.getTitle());
                    return course;
                })
                .orElse(null);
    }

    @Override
    public UnitDeleteInfo getUnitDeleteInfo(long unitId) {
        return unitDao.findById(unitId)
                .map(unitEntity -> {
                    UnitDeleteInfo unit = new UnitDeleteInfo();
                    unit.setTitle(unitEntity.getTitle());
                    return unit;
                })
                .orElse(null);
    }

    @Override
    public void deleteUnit(long id) {
        unitDao.deleteById(id);
    }

    @Override
    public LessonListCourseInfo getLessonListCourseInfo(long unitId) {
        return unitDao.findById(unitId)
                .map(unitEntity -> unitEntity.getCourse())
                .map(courseEntity -> {
                    LessonListCourseInfo course = new LessonListCourseInfo();
                    course.setTitle(courseEntity.getTitle());
                    return course;
                })
                .orElse(null);
    }

    @Override
    public LessonListUnitInfo getLessonListUnitInfo(long unitId) {
        return unitDao.findById(unitId)
                .map(unitEntity -> {
                    LessonListUnitInfo unit = new LessonListUnitInfo();
                    unit.setTitle(unitEntity.getTitle());
                    return unit;
                })
                .orElse(null);
    }

    @Override
    public List<LessonListItemInfo> getLessonList(long unitId) {
        return unitDao.findById(unitId)
                .map(unitEntity -> {
                    return unitEntity.getLessonList()
                            .stream()
                            .map(lessonEntity -> {
                                LessonListItemInfo lesson = new LessonListItemInfo();
                                lesson.setId(lessonEntity.getId());
                                lesson.setTitle(lessonEntity.getTitle());
                                lesson.setDescription(lessonEntity.getDescription());
                                return lesson;
                            })
                            .collect(Collectors.toList());
                })
                .orElse(null);
    }

    @Override
    public LessonCreateCourseInfo getLessonCreateCourseInfo(long unitId) {
        return unitDao.findById(unitId)
                .map(unitEntity -> unitEntity.getCourse())
                .map(courseEntity -> {
                    LessonCreateCourseInfo course = new LessonCreateCourseInfo();
                    course.setTitle(courseEntity.getTitle());
                    return course;
                })
                .orElse(null);
    }

    @Override
    public LessonCreateUnitInfo getLessonCreateUnitInfo(long unitId) {
        return unitDao.findById(unitId)
                .map(unitEntity -> {
                    LessonCreateUnitInfo unit = new LessonCreateUnitInfo();
                    unit.setTitle(unitEntity.getTitle());
                    return unit;
                })
                .orElse(null);
    }

    @Transactional
    @Override
    public void createLesson(long unitId, LessonCreateInfo lessonCreateInfo)
            throws LessonCreateInfoNotValidException {
        Set<ConstraintViolation<LessonCreateInfo>> constraintViolationSet =
                validator.validate(lessonCreateInfo);
        if (!constraintViolationSet.isEmpty()) {
            throw new LessonCreateInfoNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }

        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setTitle(lessonCreateInfo.getTitle());
        lessonEntity.setDescription(lessonCreateInfo.getDescription());
        lessonEntity.setOrdinal(0);// TODO: set proper value
        lessonEntity.setUnitId(unitId);

        lessonDao.save(lessonEntity);
    }
}

package com.gravityray.basiclearningsystem.unit.service;

import com.gravityray.basiclearningsystem.admin.model.CreateUnitForm;
import com.gravityray.basiclearningsystem.admin.model.EditUnitForm;
import com.gravityray.basiclearningsystem.course.service.EditUnitFormNotValidException;
import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.unit.dao.UnitDao;
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
public class DefaultUnitService implements UnitService {

    private final UnitDao unitDao;
    private final Validator validator;

    public DefaultUnitService(
            UnitDao unitDao,
            Validator validator) {
        this.unitDao = unitDao;
        this.validator = validator;
    }

    @Override
    public UnitEntity getUnit(long id) {
        return unitDao.findById(id).orElse(null);
    }

    @Override
    public long addUnit(UnitEntity unitEntity) {
        return unitDao.save(unitEntity).getId();
    }

    @Override
    public void updateUnit(UnitEntity unitEntity) {
        unitDao.save(unitEntity);
    }

    @Override
    public void changeUnitOrdinal(long unitId, int delta) {
        unitDao.findById(unitId)
                .ifPresent(unitEntity -> unitEntity.setOrdinal(unitEntity.getOrdinal() + delta));
    }

    @Override
    public void deleteUnit(long id) {
        unitDao.deleteById(id);
    }

    @Override
    public List<LessonEntity> getUnitLessons(long unitId) {
        // TODO
        return new ArrayList<>();
    }

    @Transactional
    @Override
    public void createUnit(long courseId, CreateUnitForm createUnitForm)
            throws CreateUnitFormNotValidException {
        Set<ConstraintViolation<CreateUnitForm>> constraintViolationSet =
                validator.validate(createUnitForm);
        if (!constraintViolationSet.isEmpty()) {
            throw new CreateUnitFormNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }

        UnitEntity unitEntity = new UnitEntity();
        unitEntity.setTitle(createUnitForm.getTitle());
        unitEntity.setDescription(createUnitForm.getDescription());
        unitEntity.setOrdinal(0);// TODO: set proper value
        unitEntity.setCourseId(courseId);

        unitDao.save(unitEntity);
    }

    @Transactional
    @Override
    public void updateUnit(Long courseId, EditUnitForm editUnitForm) throws EditUnitFormNotValidException {
        Set<ConstraintViolation<EditUnitForm>> constraintViolationSet =
                validator.validate(editUnitForm);
        if (!constraintViolationSet.isEmpty()) {
            throw new EditUnitFormNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }
        unitDao.findById(editUnitForm.getId())
            .ifPresent(unitEntity -> {
                unitEntity.setTitle(editUnitForm.getTitle());
                unitEntity.setDescription(editUnitForm.getDescription());
            });
    }
}

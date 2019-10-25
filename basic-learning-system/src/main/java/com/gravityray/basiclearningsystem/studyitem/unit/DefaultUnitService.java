package com.gravityray.basiclearningsystem.studyitem.unit;

import com.gravityray.basiclearningsystem.adminpanel.unit.EditUnitForm;
import com.gravityray.basiclearningsystem.adminpanel.unit.EditUnitFormNotValidException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
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

    @Transactional
    @Override
    public void updateUnit(EditUnitForm editUnitForm) throws EditUnitFormNotValidException {
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

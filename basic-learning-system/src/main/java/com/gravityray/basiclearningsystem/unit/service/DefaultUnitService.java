package com.gravityray.basiclearningsystem.unit.service;

import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.unit.dao.UnitDao;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultUnitService implements UnitService {

    private final UnitDao unitDao;

    public DefaultUnitService(UnitDao unitDao) {
        this.unitDao = unitDao;
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
}

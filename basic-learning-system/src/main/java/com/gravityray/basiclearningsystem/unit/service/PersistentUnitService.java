package com.gravityray.basiclearningsystem.unit.service;

import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.unit.dao.UnitDao;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentUnitService implements UnitService {

    private final UnitDao unitDao;

    public PersistentUnitService(UnitDao unitDao) {
        this.unitDao = unitDao;
    }

    @Override
    public UnitEntity getUnit(long id) {
        return unitDao.getUnit(id);
    }

    @Override
    public long addUnit(UnitEntity unitEntity) {
        return unitDao.addUnit(unitEntity);
    }

    @Override
    public void updateUnit(UnitEntity unitEntity) {
        unitDao.updateUnit(unitEntity);
    }

    @Override
    public void changeUnitOrdinal(long unitId, int delta) {
        unitDao.changeUnitOrdinal(unitId, delta);
    }

    @Override
    public void deleteUnit(long id) {
        unitDao.deleteUnit(id);
    }

    @Override
    public List<LessonEntity> getUnitLessons(long unitId) {
        return unitDao.getUnitLessons(unitId);
    }
}

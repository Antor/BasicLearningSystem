package com.gravityray.basiclearningsystem.unit.dao;

import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaUnitDao implements UnitDao {

    @Override
    public UnitEntity getUnit(long id) {
        return null;
    }

    @Override
    public long addUnit(UnitEntity unitEntity) {
        return 0;
    }

    @Override
    public void updateUnit(UnitEntity unitEntity) {

    }

    @Override
    public void changeUnitOrdinal(long unitId, int delta) {

    }

    @Override
    public void deleteUnit(long id) {

    }

    @Override
    public List<LessonEntity> getUnitLessons(long unitId) {
        return null;
    }
}

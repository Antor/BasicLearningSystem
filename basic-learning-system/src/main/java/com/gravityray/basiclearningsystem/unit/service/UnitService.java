package com.gravityray.basiclearningsystem.unit.service;

import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;

import java.util.List;

public interface UnitService {

    UnitEntity getUnit(long id);

    long addUnit(UnitEntity unitEntity);

    void updateUnit(UnitEntity unitEntity);

    void changeUnitOrdinal(long unitId, int delta);

    void deleteUnit(long id);

    List<LessonEntity> getUnitLessons(long unitId);
}

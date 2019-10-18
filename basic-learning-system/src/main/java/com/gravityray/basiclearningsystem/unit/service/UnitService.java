package com.gravityray.basiclearningsystem.unit.service;

import com.gravityray.basiclearningsystem.admin.model.CreateUnitForm;
import com.gravityray.basiclearningsystem.admin.model.EditUnitForm;
import com.gravityray.basiclearningsystem.course.service.EditUnitFormNotValidException;
import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;

import java.util.List;

@SuppressWarnings("unused")
public interface UnitService {

    UnitEntity getUnit(long id);

    long addUnit(UnitEntity unitEntity);

    void updateUnit(UnitEntity unitEntity);

    void changeUnitOrdinal(long unitId, int delta);

    void deleteUnit(long id);

    void createUnit(long courseId, CreateUnitForm createUnitForm) throws CreateUnitFormNotValidException;

    void updateUnit(EditUnitForm editUnitForm) throws EditUnitFormNotValidException;
}

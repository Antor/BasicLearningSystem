package com.gravityray.basiclearningsystem.studyitem.unit;

import com.gravityray.basiclearningsystem.adminpanel.unit.CreateUnitForm;
import com.gravityray.basiclearningsystem.adminpanel.unit.EditUnitForm;
import com.gravityray.basiclearningsystem.studyitem.course.EditUnitFormNotValidException;

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

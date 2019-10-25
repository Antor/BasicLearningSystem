package com.gravityray.basiclearningsystem.studyitem.unit;

import com.gravityray.basiclearningsystem.adminpanel.unit.EditUnitForm;
import com.gravityray.basiclearningsystem.adminpanel.unit.EditUnitFormNotValidException;

@SuppressWarnings("unused")
public interface UnitService {

    UnitEntity getUnit(long id);

    long addUnit(UnitEntity unitEntity);

    void updateUnit(UnitEntity unitEntity);

    void changeUnitOrdinal(long unitId, int delta);

    void deleteUnit(long id);

    void updateUnit(EditUnitForm editUnitForm) throws EditUnitFormNotValidException;
}

package com.gravityray.basiclearningsystem.adminpanel.unit;

import java.util.List;

public interface AdminPanelUnitService {

    UnitEditCourseInfo getUnitEditCourseInfo(long unitId);

    UnitEditInfo getUnitEditInfo(long unitId);

    void updateUnit(long unitId, UnitEditInfo unitEditInfo)
            throws UnitEditInfoNotValidException;

    long getUnitCourseId(long unitId);

    UnitDeleteCourseInfo getUnitDeleteCourseInfo(long unitId);

    UnitDeleteInfo getUnitDeleteInfo(long unitId);

    void deleteUnit(long id);

    LessonListCourseInfo getLessonListCourseInfo(long unitId);

    LessonListUnitInfo getLessonListUnitInfo(long unitId);

    List<LessonListItemInfo> getLessonList(long unitId);

    LessonCreateCourseInfo getLessonCreateCourseInfo(long unitId);

    LessonCreateUnitInfo getLessonCreateUnitInfo(long unitId);

    void createLesson(long unitId, LessonCreateInfo lessonCreateInfo)
            throws LessonCreateInfoNotValidException;
}

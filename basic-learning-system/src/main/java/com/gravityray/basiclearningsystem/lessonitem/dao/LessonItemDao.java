package com.gravityray.basiclearningsystem.lessonitem.dao;

import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;

import java.util.List;

public interface LessonItemDao {



    LessonItemEntity getLessonItem(long id);

    long addLessonItem(LessonItemEntity lessonItemEntity);

    void updateLessonItem(LessonItemEntity lessonItemEntity);

    void changeLessonItemOrdinal(long lessonItemId, int delta);

    void deleteLessonItem(long id);

    void completeLessonItem(long userId, long lessonItemId);
}

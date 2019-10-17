package com.gravityray.basiclearningsystem.lessonitem.service;

import com.gravityray.basiclearningsystem.admin.model.CreateLessonItemForm;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;

@SuppressWarnings("unused")
public interface LessonItemService {

    LessonItemEntity getLessonItem(long id);

    long addLessonItem(LessonItemEntity lessonItemEntity);

    void updateLessonItem(LessonItemEntity lessonItemEntity);

    void changeLessonItemOrdinal(long lessonItemId, int delta);

    void deleteLessonItem(long id);

    void completeLessonItem(long userId, long lessonItemId);

    void createLessonItem(long lessonId, CreateLessonItemForm createLessonItemForm)
            throws CreateLessonItemFormNotValidException;
}

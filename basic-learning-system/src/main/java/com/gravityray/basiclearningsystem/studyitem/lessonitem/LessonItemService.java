package com.gravityray.basiclearningsystem.studyitem.lessonitem;

import com.gravityray.basiclearningsystem.adminpanel.lessonitem.CreateLessonItemForm;
import com.gravityray.basiclearningsystem.adminpanel.lessonitem.EditLessonItemForm;

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

    void updateLessonItem(EditLessonItemForm editLessonItemForm)
            throws EditLessonItemFormNotValidException;
}

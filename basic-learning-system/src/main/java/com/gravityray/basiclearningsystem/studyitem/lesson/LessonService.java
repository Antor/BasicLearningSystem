package com.gravityray.basiclearningsystem.studyitem.lesson;

import com.gravityray.basiclearningsystem.adminpanel.lesson.CreateLessonForm;
import com.gravityray.basiclearningsystem.adminpanel.lesson.EditLessonForm;

public interface LessonService {

    LessonEntity getLesson(long id);

    long addLesson(LessonEntity lessonEntity);

    void updateLesson(LessonEntity lessonEntity);

    void changeLessonOrdinal(long lessonId, int delta);

    void deleteLesson(long id);

    void createLesson(long unitId, CreateLessonForm createLessonForm)
            throws CreateLessonFormNotValidException;

    void updateLesson(EditLessonForm editLessonForm)
            throws EditLessonFormNotValidException;
}

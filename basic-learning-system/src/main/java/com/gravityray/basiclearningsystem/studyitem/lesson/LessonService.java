package com.gravityray.basiclearningsystem.studyitem.lesson;

import com.gravityray.basiclearningsystem.adminpanel.unit.LessonCreateInfo;
import com.gravityray.basiclearningsystem.adminpanel.lesson.EditLessonForm;

public interface LessonService {

    LessonEntity getLesson(long id);

    long addLesson(LessonEntity lessonEntity);

    void updateLesson(LessonEntity lessonEntity);

    void changeLessonOrdinal(long lessonId, int delta);

    void deleteLesson(long id);



    void updateLesson(EditLessonForm editLessonForm)
            throws EditLessonFormNotValidException;
}

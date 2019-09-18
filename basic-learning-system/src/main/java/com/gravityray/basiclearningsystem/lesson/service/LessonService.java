package com.gravityray.basiclearningsystem.lesson.service;

import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;

import java.util.List;

public interface LessonService {

    LessonEntity getLesson(long id);

    long addLesson(LessonEntity lessonEntity);

    void updateLesson(LessonEntity lessonEntity);

    void changeLessonOrdinal(long lessonId, int delta);

    void deleteLesson(long id);

    List<LessonItemEntity> getLessonLessonItems(long lessonId);
}

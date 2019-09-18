package com.gravityray.basiclearningsystem.lesson.dao;

import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;

import java.util.List;

public class JpaLessonDao implements LessonDao {
    @Override
    public LessonEntity getLesson(long id) {
        return null;
    }

    @Override
    public long addLesson(LessonEntity lessonEntity) {
        return 0;
    }

    @Override
    public void updateLesson(LessonEntity lessonEntity) {

    }

    @Override
    public void changeLessonOrdinal(long lessonId, int delta) {

    }

    @Override
    public void deleteLesson(long id) {

    }

    @Override
    public List<LessonItemEntity> getLessonLessonItems(long lessonId) {
        return null;
    }
}

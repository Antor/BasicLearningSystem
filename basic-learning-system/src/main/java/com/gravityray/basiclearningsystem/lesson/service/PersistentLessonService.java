package com.gravityray.basiclearningsystem.lesson.service;

import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentLessonService implements LessonService {

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

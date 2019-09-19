package com.gravityray.basiclearningsystem.lesson.service;

import com.gravityray.basiclearningsystem.lesson.dao.LessonDao;
import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentLessonService implements LessonService {

    private final LessonDao lessonDao;

    public PersistentLessonService(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public LessonEntity getLesson(long id) {
        return lessonDao.getLesson(id);
    }

    @Override
    public long addLesson(LessonEntity lessonEntity) {
        return lessonDao.addLesson(lessonEntity);
    }

    @Override
    public void updateLesson(LessonEntity lessonEntity) {
        lessonDao.updateLesson(lessonEntity);
    }

    @Override
    public void changeLessonOrdinal(long lessonId, int delta) {
        lessonDao.changeLessonOrdinal(lessonId, delta);
    }

    @Override
    public void deleteLesson(long id) {
        lessonDao.deleteLesson(id);
    }

    @Override
    public List<LessonItemEntity> getLessonLessonItems(long lessonId) {
        return lessonDao.getLessonLessonItems(lessonId);
    }
}

package com.gravityray.basiclearningsystem.lesson.service;

import com.gravityray.basiclearningsystem.lesson.dao.LessonDao;
import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersistentLessonService implements LessonService {

    private final LessonDao lessonDao;

    public PersistentLessonService(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public LessonEntity getLesson(long id) {
        return lessonDao.findById(id).orElse(null);
    }

    @Override
    public long addLesson(LessonEntity lessonEntity) {
        return lessonDao.save(lessonEntity).getId();
    }

    @Override
    public void updateLesson(LessonEntity lessonEntity) {
        lessonDao.save(lessonEntity);
    }

    @Override
    public void changeLessonOrdinal(long lessonId, int delta) {
        // TODO
    }

    @Override
    public void deleteLesson(long id) {
        lessonDao.deleteById(id);
    }

    @Override
    public List<LessonItemEntity> getLessonLessonItems(long lessonId) {
        // TODO
        return new ArrayList<>();
    }
}

package com.gravityray.basiclearningsystem.lessonitem.service;

import com.gravityray.basiclearningsystem.lessonitem.dao.LessonItemDao;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;
import org.springframework.stereotype.Service;

@Service
public class PersistentLessonItemService implements LessonItemService {

    private final LessonItemDao lessonItemDao;

    public PersistentLessonItemService(LessonItemDao lessonItemDao) {
        this.lessonItemDao = lessonItemDao;
    }

    @Override
    public LessonItemEntity getLessonItem(long id) {
        return lessonItemDao.getLessonItem(id);
    }

    @Override
    public long addLessonItem(LessonItemEntity lessonItemEntity) {
        return lessonItemDao.addLessonItem(lessonItemEntity);
    }

    @Override
    public void updateLessonItem(LessonItemEntity lessonItemEntity) {
        lessonItemDao.updateLessonItem(lessonItemEntity);
    }

    @Override
    public void changeLessonItemOrdinal(long lessonItemId, int delta) {
        lessonItemDao.changeLessonItemOrdinal(lessonItemId, delta);
    }

    @Override
    public void deleteLessonItem(long id) {
        lessonItemDao.deleteLessonItem(id);
    }

    @Override
    public void completeLessonItem(long userId, long lessonItemId) {
        lessonItemDao.completeLessonItem(userId, lessonItemId);
    }
}

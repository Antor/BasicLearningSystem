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
        return lessonItemDao.findById(id).orElse(null);
    }

    @Override
    public long addLessonItem(LessonItemEntity lessonItemEntity) {
        return lessonItemDao.save(lessonItemEntity).getId();
    }

    @Override
    public void updateLessonItem(LessonItemEntity lessonItemEntity) {
        lessonItemDao.save(lessonItemEntity);
    }

    @Override
    public void changeLessonItemOrdinal(long lessonItemId, int delta) {
        // TODO
    }

    @Override
    public void deleteLessonItem(long id) {
        lessonItemDao.deleteById(id);
    }

    @Override
    public void completeLessonItem(long userId, long lessonItemId) {
        // TODO
    }
}

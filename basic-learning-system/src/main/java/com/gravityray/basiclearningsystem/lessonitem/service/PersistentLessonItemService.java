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
        return null;
    }

    @Override
    public long addLessonItem(LessonItemEntity lessonItemEntity) {
        return 0;
    }

    @Override
    public void updateLessonItem(LessonItemEntity lessonItemEntity) {

    }

    @Override
    public void changeLessonItemOrdinal(long lessonItemId, int delta) {

    }

    @Override
    public void deleteLessonItem(long id) {

    }

    @Override
    public void completeLessonItem(long userId, long lessonItemId) {

    }
}

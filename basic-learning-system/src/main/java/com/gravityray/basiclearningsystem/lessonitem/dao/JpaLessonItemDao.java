package com.gravityray.basiclearningsystem.lessonitem.dao;

import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaLessonItemDao implements LessonItemDao {

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

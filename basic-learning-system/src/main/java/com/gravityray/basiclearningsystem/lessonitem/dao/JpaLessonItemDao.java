package com.gravityray.basiclearningsystem.lessonitem.dao;

import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class JpaLessonItemDao implements LessonItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LessonItemEntity getLessonItem(long id) {
        return entityManager.find(LessonItemEntity.class, id);
    }

    @Transactional
    @Override
    public long addLessonItem(LessonItemEntity lessonItemEntity) {
        entityManager.persist(lessonItemEntity);
        entityManager.flush();
        return lessonItemEntity.getId();
    }


    @Transactional
    @Override
    public void updateLessonItem(LessonItemEntity lessonItemEntity) {
        entityManager.merge(lessonItemEntity);
    }

    @Transactional
    @Override
    public void changeLessonItemOrdinal(long lessonItemId, int delta) {
        // TODO
    }

    @Transactional
    @Override
    public void deleteLessonItem(long id) {
        Query query = entityManager.createNativeQuery("DELETE FROM lesson_items where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void completeLessonItem(long userId, long lessonItemId) {
        // TODO
    }
}

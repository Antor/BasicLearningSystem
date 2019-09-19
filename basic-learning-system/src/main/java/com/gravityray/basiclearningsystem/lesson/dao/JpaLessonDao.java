package com.gravityray.basiclearningsystem.lesson.dao;

import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class JpaLessonDao implements LessonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LessonEntity getLesson(long id) {
        return entityManager.find(LessonEntity.class, id);
    }

    @Transactional
    @Override
    public long addLesson(LessonEntity lessonEntity) {
        entityManager.persist(lessonEntity);
        entityManager.flush();
        return lessonEntity.getId();
    }

    @Transactional
    @Override
    public void updateLesson(LessonEntity lessonEntity) {
        entityManager.merge(lessonEntity);
    }

    @Transactional
    @Override
    public void changeLessonOrdinal(long lessonId, int delta) {
        // TODO
    }

    @Transactional
    @Override
    public void deleteLesson(long id) {
        Query query = entityManager.createNativeQuery("DELETE FROM lessons where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();

        // TODO: delete lesson content
    }

    @Override
    public List<LessonItemEntity> getLessonLessonItems(long lessonId) {
        // TODO
        return null;
    }
}

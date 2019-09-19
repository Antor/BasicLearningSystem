package com.gravityray.basiclearningsystem.unit.dao;

import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class JpaUnitDao implements UnitDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UnitEntity getUnit(long id) {
        return entityManager.find(UnitEntity.class, id);
    }

    @Transactional
    @Override
    public long addUnit(UnitEntity unitEntity) {
        entityManager.persist(unitEntity);
        entityManager.flush();
        return unitEntity.getId();
    }

    @Transactional
    @Override
    public void updateUnit(UnitEntity unitEntity) {
        entityManager.merge(unitEntity);
    }

    @Transactional
    @Override
    public void changeUnitOrdinal(long unitId, int delta) {

    }

    @Transactional
    @Override
    public void deleteUnit(long id) {
        Query query = entityManager.createNativeQuery("DELETE FROM units where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();

        // TODO: delete unit content
    }

    @Override
    public List<LessonEntity> getUnitLessons(long unitId) {
        return null;
    }
}

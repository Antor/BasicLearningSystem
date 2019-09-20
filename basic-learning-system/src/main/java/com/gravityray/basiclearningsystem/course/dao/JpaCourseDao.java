package com.gravityray.basiclearningsystem.course.dao;

import com.gravityray.basiclearningsystem.course.model.CourseEntity;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class JpaCourseDao implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CourseEntity> getCourses(boolean onlyActive) {
        String queryString = onlyActive
                ? "SELECT c FROM courses c WHERE c.active = TRUE"
                : "SELECT c FROM courses c";
        TypedQuery<CourseEntity> query = entityManager.createQuery(
                queryString,
                CourseEntity.class);
        return query.getResultList();
    }

    @Override
    public CourseEntity getCourse(long id) {
        return entityManager.find(CourseEntity.class, id);
    }

    @Transactional
    @Override
    public long addCourse(CourseEntity courseEntity) {
        entityManager.persist(courseEntity);
        entityManager.flush();
        return courseEntity.getId();
    }

    @Transactional
    @Override
    public void updateCourse(CourseEntity courseEntity) {
        entityManager.merge(courseEntity);
    }

    @Transactional
    @Override
    public void activateCourse(long courseId) {
        CourseEntity courseEntity = getCourse(courseId);
        courseEntity.setActive(true);
    }

    @Transactional
    @Override
    public void deactivateCourse(long courseId) {
        CourseEntity courseEntity = getCourse(courseId);
        courseEntity.setActive(false);
    }

    @Transactional
    @Override
    public void deleteCourse(long id) {
        Query query = entityManager.createNativeQuery("DELETE FROM courses where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Override
    public List<UnitEntity> getCourseUnits(long courseId) {
        // TODO
        return null;
    }

    @Override
    public List<CourseEntity> getUserCourses(long userId) {
        // TODO
        return null;
    }

    @Transactional
    @Override
    public void enrollUserToCourse(long userId, long courseId) {
        // TODO
    }

    @Transactional
    @Override
    public void unenrollUserFromCourse(long userId, long courseId) {
        // TODO
    }
}

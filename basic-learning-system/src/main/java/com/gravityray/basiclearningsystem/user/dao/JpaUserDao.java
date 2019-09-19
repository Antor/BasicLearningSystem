package com.gravityray.basiclearningsystem.user.dao;

import com.gravityray.basiclearningsystem.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Qualifier("jpa")
public class JpaUserDao implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> getAllUsers() {
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT u FROM users u",
                UserEntity.class);
        return query.getResultList();
    }

    @Override
    public UserEntity getUser(long id) {
        return entityManager.find(UserEntity.class, id);
    }

    @Transactional
    @Override
    public long createUser(UserEntity user) {
        entityManager.persist(user);
        entityManager.flush();
        return user.getId();
    }

    @Transactional
    @Override
    public void updateUser(UserEntity user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        Query query = entityManager.createNativeQuery("DELETE FROM users where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }
}

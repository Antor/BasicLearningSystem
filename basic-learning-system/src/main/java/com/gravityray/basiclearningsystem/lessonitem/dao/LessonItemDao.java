package com.gravityray.basiclearningsystem.lessonitem.dao;

import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonItemDao extends CrudRepository<LessonItemEntity, Long> {
}

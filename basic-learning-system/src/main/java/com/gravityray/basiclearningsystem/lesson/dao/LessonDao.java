package com.gravityray.basiclearningsystem.lesson.dao;

import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonDao extends CrudRepository<LessonEntity, Long> {
}

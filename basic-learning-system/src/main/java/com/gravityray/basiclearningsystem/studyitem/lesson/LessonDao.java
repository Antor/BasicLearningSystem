package com.gravityray.basiclearningsystem.studyitem.lesson;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonDao extends CrudRepository<LessonEntity, Long> {
}

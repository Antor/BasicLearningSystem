package com.gravityray.basiclearningsystem.studyitem.lessonitem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonItemDao extends CrudRepository<LessonItemEntity, Long> {
}

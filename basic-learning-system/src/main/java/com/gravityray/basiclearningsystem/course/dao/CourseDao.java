package com.gravityray.basiclearningsystem.course.dao;

import com.gravityray.basiclearningsystem.course.model.entity.CourseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends CrudRepository<CourseEntity, Long> {

    @Query("SELECT c FROM courses c WHERE c.active = TRUE")
    Iterable<CourseEntity> getActiveCourses();
}

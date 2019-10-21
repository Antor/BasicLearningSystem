package com.gravityray.basiclearningsystem.studyitem.unit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitDao extends CrudRepository<UnitEntity, Long> {
}

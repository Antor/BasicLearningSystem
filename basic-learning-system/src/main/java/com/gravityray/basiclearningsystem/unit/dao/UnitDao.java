package com.gravityray.basiclearningsystem.unit.dao;

import com.gravityray.basiclearningsystem.unit.model.UnitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitDao extends CrudRepository<UnitEntity, Long> {
}

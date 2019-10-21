package com.gravityray.basiclearningsystem.studyitem.unit;

import org.springframework.stereotype.Component;

@Component
public class UnitConverter {

    public UnitDto toDto(UnitEntity unitEntity) {
        UnitDto unitDto = new UnitDto();

        unitDto.setId(unitEntity.getId());
        unitDto.setTitle(unitEntity.getTitle());
        unitDto.setDescription(unitEntity.getDescription());
        unitDto.setOrdinal(unitEntity.getOrdinal());
        unitDto.setCourseId(unitEntity.getCourseId());

        return unitDto;
    }

    public UnitEntity toEntity(UnitDto unitDto) {
        UnitEntity unitEntity = new UnitEntity();

        unitEntity.setId(unitDto.getId());
        unitEntity.setTitle(unitDto.getTitle());
        unitEntity.setDescription(unitDto.getDescription());
        unitEntity.setOrdinal(unitDto.getOrdinal());
        unitEntity.setCourseId(unitDto.getCourseId());

        return unitEntity;
    }
}

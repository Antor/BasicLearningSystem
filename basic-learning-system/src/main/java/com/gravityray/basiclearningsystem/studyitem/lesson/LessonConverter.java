package com.gravityray.basiclearningsystem.studyitem.lesson;

import org.springframework.stereotype.Component;

@Component
public class LessonConverter {

    public LessonDto toDto(LessonEntity lessonEntity) {
        LessonDto lessonDto = new LessonDto();

        lessonDto.setId(lessonEntity.getId());
        lessonDto.setTitle(lessonEntity.getTitle());
        lessonDto.setDescription(lessonEntity.getDescription());
        lessonDto.setOrdinal(lessonEntity.getOrdinal());
        lessonDto.setUnitId(lessonEntity.getUnitId());

        return lessonDto;
    }

    public LessonEntity toEntity(LessonDto lessonDto) {
        LessonEntity lessonEntity = new LessonEntity();

        lessonEntity.setId(lessonDto.getId());
        lessonEntity.setTitle(lessonDto.getTitle());
        lessonEntity.setDescription(lessonDto.getDescription());
        lessonEntity.setOrdinal(lessonDto.getOrdinal());
        lessonEntity.setUnitId(lessonDto.getUnitId());

        return lessonEntity;
    }
}

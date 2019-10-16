package com.gravityray.basiclearningsystem.lessonitem.service;

import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemDto;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;
import org.springframework.stereotype.Component;

@Component
public class LessonItemConverter {

    public LessonItemDto toDto(LessonItemEntity lessonItemEntity) {
        LessonItemDto lessonItemDto = new LessonItemDto();

        lessonItemDto.setId(lessonItemEntity.getId());
        lessonItemDto.setTitle(lessonItemEntity.getTitle());
        lessonItemDto.setContent(lessonItemEntity.getContent());
        lessonItemDto.setOrdinal(lessonItemEntity.getOrdinal());
        lessonItemDto.setLessonId(lessonItemEntity.getLessonId());

        return lessonItemDto;
    }

    public LessonItemEntity toEntity(LessonItemDto lessonItemDto) {
        LessonItemEntity lessonItemEntity = new LessonItemEntity();

        lessonItemEntity.setId(lessonItemDto.getId());
        lessonItemEntity.setTitle(lessonItemDto.getTitle());
        lessonItemEntity.setContent(lessonItemDto.getContent());
        lessonItemEntity.setOrdinal(lessonItemDto.getOrdinal());
        lessonItemEntity.setLessonId(lessonItemDto.getLessonId());

        return lessonItemEntity;
    }
}

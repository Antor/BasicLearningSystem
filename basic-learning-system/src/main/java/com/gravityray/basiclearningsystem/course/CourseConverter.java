package com.gravityray.basiclearningsystem.course;

import com.gravityray.basiclearningsystem.course.model.*;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter {

    public CourseDto toDto(CourseEntity courseEntity) {
        CourseDto courseDto = new CourseDto();

        courseDto.setId(courseEntity.getId());
        courseDto.setTitle(courseEntity.getTitle());
        courseDto.setDescription(courseEntity.getDescription());
        courseDto.setActive(courseEntity.isActive());

        return courseDto;
    }

    public CourseEntity toEntity(CourseDto courseDto) {
        CourseEntity courseEntity = new CourseEntity();

        courseEntity.setId(courseDto.getId());
        courseEntity.setTitle(courseDto.getTitle());
        courseEntity.setDescription(courseDto.getDescription());
        courseEntity.setActive(courseDto.isActive());

        return courseEntity;
    }

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

package com.gravityray.basiclearningsystem.studyitem.course;

import org.springframework.stereotype.Service;

@Service
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

}

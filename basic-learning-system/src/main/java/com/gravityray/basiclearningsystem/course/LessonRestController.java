package com.gravityray.basiclearningsystem.course;


import com.gravityray.basiclearningsystem.course.model.ChangeOrdinalRequest;
import com.gravityray.basiclearningsystem.course.model.LessonDto;
import com.gravityray.basiclearningsystem.course.model.LessonEntity;
import com.gravityray.basiclearningsystem.course.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class LessonRestController {

    private final CourseService courseService;
    private final CourseConverter courseConverter;

    public LessonRestController(
            CourseService courseService,
            CourseConverter courseConverter) {
        this.courseService = courseService;
        this.courseConverter = courseConverter;
    }

    @GetMapping("/lesson/{lessonId}")
    public LessonDto getLesson(@PathVariable long lessonId) {
        LessonEntity lessonEntity = courseService.getLesson(lessonId);
        LessonDto lessonDto = courseConverter.toDto(lessonEntity);

        lessonDto.setLessonItems(
                courseService.getLessonLessonItems(lessonId)
                        .stream()
                        .map(courseConverter::toDto)
                        .collect(Collectors.toList()));

        return lessonDto;
    }

    @PostMapping("/lesson")
    public LessonDto createLesson(@RequestBody LessonDto lesson) {
        long lessonId = courseService.addLesson(courseConverter.toEntity(lesson));
        lesson.setId(lessonId);
        return lesson;
    }

    @PutMapping("/lesson")
    public void updateLesson(@RequestBody LessonDto lesson) {
        courseService.updateLesson(courseConverter.toEntity(lesson));
    }

    @PostMapping("/change-lesson-ordinal")
    public void changeLessonOrdinal(@RequestBody ChangeOrdinalRequest request) {
        courseService.changeLessonOrdinal(request.getId(), request.getDelta());
    }

    @DeleteMapping("/lesson/{lessonId}")
    public void deleteLesson(@PathVariable long lessonId) {
        courseService.deleteLesson(lessonId);
    }
}

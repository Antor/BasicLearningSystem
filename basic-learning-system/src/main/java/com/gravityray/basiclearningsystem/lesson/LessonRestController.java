package com.gravityray.basiclearningsystem.lesson;


import com.gravityray.basiclearningsystem.course.model.dto.ChangeOrdinalRequest;
import com.gravityray.basiclearningsystem.lesson.model.LessonDto;
import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;
import com.gravityray.basiclearningsystem.lesson.service.LessonService;
import com.gravityray.basiclearningsystem.lessonitem.LessonItemConverter;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class LessonRestController {

    private final LessonService courseService;
    private final LessonConverter lessonConverter;
    private final LessonItemConverter lessonItemConverter;

    public LessonRestController(
            LessonService courseService,
            LessonConverter lessonConverter,
            LessonItemConverter lessonItemConverter) {
        this.courseService = courseService;
        this.lessonConverter = lessonConverter;
        this.lessonItemConverter = lessonItemConverter;
    }

    @GetMapping("/lesson/{lessonId}")
    public LessonDto getLesson(@PathVariable long lessonId) {
        LessonEntity lessonEntity = courseService.getLesson(lessonId);
        LessonDto lessonDto = lessonConverter.toDto(lessonEntity);

        lessonDto.setLessonItems(
                courseService.getLessonLessonItems(lessonId)
                        .stream()
                        .map(lessonItemConverter::toDto)
                        .collect(Collectors.toList()));

        return lessonDto;
    }

    @PostMapping("/lesson")
    public LessonDto createLesson(@RequestBody LessonDto lesson) {
        long lessonId = courseService.addLesson(lessonConverter.toEntity(lesson));
        lesson.setId(lessonId);
        return lesson;
    }

    @PutMapping("/lesson")
    public void updateLesson(@RequestBody LessonDto lesson) {
        courseService.updateLesson(lessonConverter.toEntity(lesson));
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

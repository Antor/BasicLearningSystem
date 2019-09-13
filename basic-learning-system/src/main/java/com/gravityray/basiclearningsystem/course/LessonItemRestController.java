package com.gravityray.basiclearningsystem.course;

import com.gravityray.basiclearningsystem.course.model.ChangeOrdinalRequest;
import com.gravityray.basiclearningsystem.course.model.LessonItemDto;
import com.gravityray.basiclearningsystem.course.model.LessonItemEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LessonItemRestController {

    private final CourseService courseService;
    private final CourseConverter courseConverter;

    public LessonItemRestController(
            CourseService courseService,
            CourseConverter courseConverter) {
        this.courseService = courseService;
        this.courseConverter = courseConverter;
    }

    @GetMapping("/lesson-item/{lessonItemId}")
    public LessonItemDto getLessonItem(@PathVariable long lessonItemId) {
        LessonItemEntity lessonItemEntity = courseService.getLessonItem(lessonItemId);
        LessonItemDto lessonItemDto = courseConverter.toDto(lessonItemEntity);
        return lessonItemDto;
    }

    @PostMapping("/lesson-item")
    public LessonItemDto createLessonItem(@RequestBody LessonItemDto lessonItem) {
        long lessonItemId = courseService.addLessonItem(courseConverter.toEntity(lessonItem));
        lessonItem.setId(lessonItemId);
        return lessonItem;
    }

    @PutMapping("/lesson-item")
    public void updateLessonItem(@RequestBody LessonItemDto lessonItem) {
        courseService.updateLessonItem(courseConverter.toEntity(lessonItem));
    }

    @PostMapping("/change-lesson-item-ordinal")
    public void changeLessonItemOrdinal(@RequestBody ChangeOrdinalRequest request) {
        courseService.changeLessonItemOrdinal(request.getId(), request.getDelta());
    }

    @DeleteMapping("/lesson-item/{lessonItemId}")
    public void deleteLessonItem(@PathVariable long lessonItemId) {
        courseService.deleteLessonItem(lessonItemId);
    }
}

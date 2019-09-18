package com.gravityray.basiclearningsystem.lessonitem;

import com.gravityray.basiclearningsystem.course.CourseConverter;
import com.gravityray.basiclearningsystem.course.model.ChangeOrdinalRequest;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemDto;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;
import com.gravityray.basiclearningsystem.lessonitem.service.LessonItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LessonItemRestController {

    private final LessonItemService lessonItemService;
    private final CourseConverter courseConverter;

    public LessonItemRestController(
            LessonItemService lessonItemService,
            CourseConverter courseConverter) {
        this.lessonItemService = lessonItemService;
        this.courseConverter = courseConverter;
    }

    @GetMapping("/lesson-item/{lessonItemId}")
    public LessonItemDto getLessonItem(@PathVariable long lessonItemId) {
        LessonItemEntity lessonItemEntity = lessonItemService.getLessonItem(lessonItemId);
        LessonItemDto lessonItemDto = courseConverter.toDto(lessonItemEntity);
        return lessonItemDto;
    }

    @PostMapping("/lesson-item")
    public LessonItemDto createLessonItem(@RequestBody LessonItemDto lessonItem) {
        long lessonItemId = lessonItemService.addLessonItem(courseConverter.toEntity(lessonItem));
        lessonItem.setId(lessonItemId);
        return lessonItem;
    }

    @PutMapping("/lesson-item")
    public void updateLessonItem(@RequestBody LessonItemDto lessonItem) {
        lessonItemService.updateLessonItem(courseConverter.toEntity(lessonItem));
    }

    @PostMapping("/change-lesson-item-ordinal")
    public void changeLessonItemOrdinal(@RequestBody ChangeOrdinalRequest request) {
        lessonItemService.changeLessonItemOrdinal(request.getId(), request.getDelta());
    }

    @DeleteMapping("/lesson-item/{lessonItemId}")
    public void deleteLessonItem(@PathVariable long lessonItemId) {
        lessonItemService.deleteLessonItem(lessonItemId);
    }
}

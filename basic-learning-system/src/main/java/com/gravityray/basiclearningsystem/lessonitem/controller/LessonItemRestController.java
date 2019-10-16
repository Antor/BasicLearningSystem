package com.gravityray.basiclearningsystem.lessonitem.controller;

import com.gravityray.basiclearningsystem.course.model.ChangeOrdinalRequest;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemDto;
import com.gravityray.basiclearningsystem.lessonitem.model.LessonItemEntity;
import com.gravityray.basiclearningsystem.lessonitem.service.LessonItemConverter;
import com.gravityray.basiclearningsystem.lessonitem.service.LessonItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LessonItemRestController {

    private final LessonItemService lessonItemService;
    private final LessonItemConverter lessonItemConverter;

    public LessonItemRestController(
            LessonItemService lessonItemService,
            LessonItemConverter lessonItemConverter) {
        this.lessonItemService = lessonItemService;
        this.lessonItemConverter = lessonItemConverter;
    }

    @GetMapping("/lesson-item/{lessonItemId}")
    public LessonItemDto getLessonItem(@PathVariable long lessonItemId) {
        LessonItemEntity lessonItemEntity = lessonItemService.getLessonItem(lessonItemId);
        LessonItemDto lessonItemDto = lessonItemConverter.toDto(lessonItemEntity);
        return lessonItemDto;
    }

    @PostMapping("/lesson-item")
    public LessonItemDto createLessonItem(@RequestBody LessonItemDto lessonItem) {
        long lessonItemId = lessonItemService.addLessonItem(lessonItemConverter.toEntity(lessonItem));
        lessonItem.setId(lessonItemId);
        return lessonItem;
    }

    @PutMapping("/lesson-item")
    public void updateLessonItem(@RequestBody LessonItemDto lessonItem) {
        lessonItemService.updateLessonItem(lessonItemConverter.toEntity(lessonItem));
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

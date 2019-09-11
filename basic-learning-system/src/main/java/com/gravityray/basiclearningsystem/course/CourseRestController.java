package com.gravityray.basiclearningsystem.course;

import com.gravityray.basiclearningsystem.course.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CourseRestController {

    private final CourseService courseService;
    private final CourseConverter courseConverter;

    public CourseRestController(
            CourseService courseService,
            CourseConverter courseConverter) {
        this.courseService = courseService;
        this.courseConverter = courseConverter;
    }

    @GetMapping("/me/course")
    public List<CourseDto> getUserCourses() {
        long userId = -1; // TODO: get from Spring Security
        return courseService.getUserCourses(userId)
                .stream()
                .map(courseConverter::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/me/enroll")
    public void enrollUserToCourse(@RequestBody Long courseId) {
        long userId = -1; // TODO: get from Spring Security
        courseService.enrollUserToCourse(userId, courseId);
    }

    @PostMapping("/me/unenroll")
    public void unenrollUserToCourse(@RequestBody Long courseId) {
        long userId = -1; // TODO: get from Spring Security
        courseService.unenrollUserFromCourse(userId, courseId);
    }

    @GetMapping("/admin/course")
    public List<CourseDto> getAllCourses() {
        return courseService.getAllCourses()
                .stream()
                .map(courseConverter::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/course")
    public List<CourseDto> getActiveCourses() {
        return courseService.getActiveCourses()
                .stream()
                .map(courseConverter::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/course/{course_id}")
    public CourseDto getCourse(@PathVariable("course_id") long courseId) {
        CourseEntity courseEntity = courseService.getCourse(courseId);
        CourseDto courseDto = courseConverter.toDto(courseEntity);
        courseDto.setUnits(
                courseService.getCourseUnits(courseId)
                        .stream()
                        .map(courseConverter::toDto)
                        .collect(Collectors.toList()));
        return courseDto;
    }

    @PostMapping("/admin/activate-course")
    public void activateCourse(@RequestBody long courseId) {
        courseService.activateCourse(courseId);
    }

    @PostMapping("/admin/deactivate-course")
    public void deactivateCourse(@RequestBody long courseId) {
        courseService.deactivateCourse(courseId);
    }

    @PostMapping("/course")
    public CourseDto createCourse(@RequestBody CourseDto course) {
        long courseId = courseService.addCourse(courseConverter.toEntity(course));
        course.setId(courseId);
        return course;
    }

    @PutMapping("/course")
    public void updateCourse(@RequestBody CourseDto course) {
        courseService.updateCourse(courseConverter.toEntity(course));
    }

    @DeleteMapping("/course/{course_id}")
    public void deleteCourse(@PathVariable("course_id") long courseId) {
        courseService.deleteCourse(courseId);
    }

    @GetMapping("/unit/{unit_id}")
    public UnitDto getUnit(@PathVariable("unit_id") long unitId) {
        UnitEntity unitEntity = courseService.getUnit(unitId);
        UnitDto unitDto = courseConverter.toDto(unitEntity);

        unitDto.setLessons(
                courseService.getUnitLessons(unitId)
                        .stream()
                        .map(courseConverter::toDto)
                        .collect(Collectors.toList()));

        return unitDto;
    }

    @PostMapping("/unit")
    public UnitDto createUnit(@RequestBody UnitDto unit) {
        long unitId = courseService.addUnit(courseConverter.toEntity(unit));
        unit.setId(unitId);
        return unit;
    }

    @PutMapping("/unit")
    public void updateUnit(@RequestBody UnitDto unit) {
        courseService.updateUnit(courseConverter.toEntity(unit));
    }

    @PostMapping("/increase-unit-ordinal")
    public void increaseUnitOrdinal(@RequestBody long unitId) {
        courseService.increaseUnitOrdinal(unitId);
    }

    @PostMapping("/decrease-unit-ordinal")
    public void decreaseUnitOrdinal(@RequestBody long unitId) {
        courseService.decreaseUnitOrdinal(unitId);
    }

    @DeleteMapping("/unit/{unit_id}")
    public void deleteUnit(@PathVariable("unit_id") long unitId) {
        courseService.deleteUnit(unitId);
    }

    @GetMapping("/lesson/{lesson_id}")
    public LessonDto getLesson(@PathVariable("lesson_id") long lessonId) {
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

    @PostMapping("/increase-lesson-ordinal")
    public void increaseLessonOrdinal(@RequestBody long lessonId) {
        courseService.increaseLessonOrdinal(lessonId);
    }

    @PostMapping("/decrease-lesson-ordinal")
    public void decreaseLessonOrdinal(@RequestBody long lessonId) {
        courseService.decreaseLessonOrdinal(lessonId);
    }

    @DeleteMapping("/lesson/{lesson_id}")
    public void deleteLesson(@PathVariable("lesson_id") long lessonId) {
        courseService.deleteLesson(lessonId);
    }

    @GetMapping("/lesson-item/{lesson_item_id}")
    public LessonItemDto getLessonItem(@PathVariable("lesson_item_id") long lessonItemId) {
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

    @PostMapping("/increase-lesson-item-ordinal")
    public void increaseLessonItemOrdinal(@RequestBody long lessonItemId) {
        courseService.increaseLessonItemOrdinal(lessonItemId);
    }

    @PostMapping("/decrease-lesson-item-ordinal")
    public void decreaseLessonItemOrdinal(@RequestBody long lessonItemId) {
        courseService.decreaseLessonItemOrdinal(lessonItemId);
    }

    @DeleteMapping("/lesson-item/{lesson_item_id}")
    public void deleteLessonItem(@PathVariable("lesson_item_id") long lessonItemId) {
        courseService.deleteLessonItem(lessonItemId);
    }
}

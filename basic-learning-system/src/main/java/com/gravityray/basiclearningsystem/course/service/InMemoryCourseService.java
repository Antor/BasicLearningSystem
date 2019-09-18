package com.gravityray.basiclearningsystem.course.service;

import com.gravityray.basiclearningsystem.course.model.CourseEntity;
import com.gravityray.basiclearningsystem.course.model.LessonEntity;
import com.gravityray.basiclearningsystem.course.model.LessonItemEntity;
import com.gravityray.basiclearningsystem.course.model.UnitEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InMemoryCourseService implements CourseService {

    private final Map<Long, CourseEntity> courseByIdMap;
    private long nextCourseId;

    private final Map<Long, UnitEntity> unitByIdMap;
    private long nextUnitId;

    private final Map<Long, LessonEntity> lessonByIdMap;
    private long nextLessonId;

    private final Map<Long, LessonItemEntity> lessonItemByIdMap;
    private long nextLessonItemId;

    private final Map<Long, Set<Long>> courseIdListByUserIdMap;

    private final Map<Long, Set<Long>> completedLessonItemIdSetByUserIdMap;

    public InMemoryCourseService() {
        courseByIdMap = new HashMap<>();
        nextCourseId = 0L;

        unitByIdMap = new HashMap<>();
        nextUnitId = 0L;

        lessonByIdMap = new HashMap<>();
        nextLessonId = 0L;

        lessonItemByIdMap = new HashMap<>();
        nextLessonItemId = 0L;

        courseIdListByUserIdMap = new HashMap<>();

        completedLessonItemIdSetByUserIdMap = new HashMap<>();
    }

    @Override
    public List<CourseEntity> getUserCourses(long userId) {
        Set<Long> courseIdSet = courseIdListByUserIdMap.get(userId);
        if (courseIdSet == null) {
            courseIdSet = new HashSet<>();
        }
        return courseIdSet.stream()
                .map(courseByIdMap::get)
                .collect(Collectors.toList());
    }

    @Override
    public void enrollUserToCourse(long userId, long courseId) {
        Set<Long> courseIdSet = courseIdListByUserIdMap.get(userId);
        if (courseIdSet == null) {
            courseIdSet = new HashSet<>();
            courseIdListByUserIdMap.put(userId, courseIdSet);
        }
        courseIdSet.add(courseId);
    }

    @Override
    public void unenrollUserFromCourse(long userId, long courseId) {
        Set<Long> courseIdSet = courseIdListByUserIdMap.get(userId);
        if (courseIdSet != null) {
            courseIdSet.remove(courseId);
        }
    }

    @Override
    public List<CourseEntity> getAllCourses() {
        return new ArrayList<>(courseByIdMap.values());
    }

    @Override
    public List<CourseEntity> getActiveCourses() {
        return courseByIdMap.values()
                .stream()
                .filter(CourseEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public CourseEntity getCourse(long id) {
        return courseByIdMap.get(id);
    }

    @Override
    public long addCourse(CourseEntity courseEntity) {
        long courseId = nextCourseId++;

        courseEntity.setId(courseId);
        courseByIdMap.put(courseId, courseEntity);

        return courseId;
    }

    @Override
    public void updateCourse(CourseEntity courseEntity) {
        courseByIdMap.put(courseEntity.getId(), courseEntity);
    }

    @Override
    public void activateCourse(long courseId) {
        CourseEntity course = courseByIdMap.get(courseId);
        if (course != null) {
            course.setActive(true);
        }
    }

    @Override
    public void deactivateCourse(long courseId) {
        CourseEntity course = courseByIdMap.get(courseId);
        if (course != null) {
            course.setActive(false);
        }
    }

    @Override
    public void deleteCourse(long id) {
        courseByIdMap.remove(id);

        unitByIdMap.values()
                .stream()
                .filter(unit -> unit.getCourseId() == id)
                .map(UnitEntity::getId)
                .collect(Collectors.toList())
                .forEach(this::deleteUnit);
    }

    @Override
    public List<UnitEntity> getCourseUnits(long courseId) {
        return unitByIdMap.values()
                .stream()
                .filter(unitEntity -> unitEntity.getCourseId() == courseId)
                .collect(Collectors.toList());
    }

    @Override
    public UnitEntity getUnit(long id) {
        return unitByIdMap.get(id);
    }

    @Override
    public long addUnit(UnitEntity unitEntity) {
        long unitId = nextUnitId++;

        unitEntity.setId(unitId);
        unitByIdMap.put(unitId, unitEntity);

        return unitId;
    }

    @Override
    public void updateUnit(UnitEntity unitEntity) {
        unitByIdMap.put(unitEntity.getId(), unitEntity);
    }

    @Override
    public void changeUnitOrdinal(long unitId, int delta) {
        UnitEntity unit = unitByIdMap.get(unitId);
        if (unit != null) {
            unit.setOrdinal(unit.getOrdinal() + delta);
        }
    }

    @Override
    public void deleteUnit(long id) {
        unitByIdMap.remove(id);

        lessonByIdMap.values()
                .stream()
                .filter(lesson -> lesson.getUnitId() == id)
                .map(LessonEntity::getId)
                .collect(Collectors.toList())
                .forEach(this::deleteLesson);
    }

    @Override
    public List<LessonEntity> getUnitLessons(long unitId) {
        return lessonByIdMap.values()
                .stream()
                .filter(lessonEntity -> lessonEntity.getUnitId() == unitId)
                .collect(Collectors.toList());
    }

    @Override
    public LessonEntity getLesson(long id) {
        return lessonByIdMap.get(id);
    }

    @Override
    public long addLesson(LessonEntity lessonEntity) {
        long lessonId = nextLessonId++;

        lessonEntity.setId(lessonId);
        lessonByIdMap.put(lessonId, lessonEntity);

        return lessonId;
    }

    @Override
    public void updateLesson(LessonEntity lessonEntity) {
        lessonByIdMap.put(lessonEntity.getId(), lessonEntity);
    }

    @Override
    public void changeLessonOrdinal(long lessonId, int delta) {
        LessonEntity lesson = lessonByIdMap.get(lessonId);
        if (lesson != null) {
            lesson.setOrdinal(lesson.getOrdinal() + delta);
        }
    }

    @Override
    public void decreaseLessonOrdinal(long lessonId) {
        LessonEntity lesson = lessonByIdMap.get(lessonId);
        if (lesson != null) {
            lesson.setOrdinal(lesson.getOrdinal() - 1);
        }
    }

    @Override
    public void deleteLesson(long id) {
        lessonByIdMap.remove(id);

        lessonItemByIdMap.values()
                .stream()
                .filter(lesson -> lesson.getLessonId() == id)
                .map(LessonItemEntity::getId)
                .collect(Collectors.toList())
                .forEach(this::deleteLessonItem);
    }

    @Override
    public List<LessonItemEntity> getLessonLessonItems(long lessonId) {
        return lessonItemByIdMap.values()
                .stream()
                .filter(lessonItemEntity -> lessonItemEntity.getLessonId() == lessonId)
                .collect(Collectors.toList());
    }

    @Override
    public LessonItemEntity getLessonItem(long id) {
        return lessonItemByIdMap.get(id);
    }

    @Override
    public long addLessonItem(LessonItemEntity lessonItemEntity) {
        long lessonItemId = nextLessonItemId++;

        lessonItemEntity.setId(lessonItemId);
        lessonItemByIdMap.put(lessonItemId, lessonItemEntity);

        return lessonItemId;
    }

    @Override
    public void updateLessonItem(LessonItemEntity lessonItemEntity) {
        lessonItemByIdMap.put(lessonItemEntity.getId(), lessonItemEntity);
    }

    @Override
    public void changeLessonItemOrdinal(long lessonItemId, int delta) {
        LessonItemEntity lessonItem = lessonItemByIdMap.get(lessonItemId);
        if (lessonItem != null) {
            lessonItem.setOrdinal(lessonItem.getOrdinal() + delta);
        }
    }

    @Override
    public void deleteLessonItem(long id) {
        lessonItemByIdMap.remove(id);
    }

    @Override
    public void completeLessonItem(long userId, long lessonItemId) {
        Set<Long> completedLessonItemIdSet = completedLessonItemIdSetByUserIdMap.get(userId);
        if (completedLessonItemIdSet == null) {
            completedLessonItemIdSet = new HashSet<>();
            completedLessonItemIdSetByUserIdMap.put(userId, completedLessonItemIdSet);
        }
        completedLessonItemIdSet.add(lessonItemId);
    }
}

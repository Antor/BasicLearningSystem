package com.gravityray.basiclearningsystem.study;

import java.util.Objects;

public class StudyItemNavigationInfo {

    public static final String TYPE_COURSE = "course";
    public static final String TYPE_UNIT = "unit";
    public static final String TYPE_LESSON = "lesson";
    public static final String TYPE_LESSON_ITEM = "lesson_item";

    private final String type;

    private final Long courseId;
    private final Long unitId;
    private final Long lessonId;
    private final Long lessonItemId;

    public static StudyItemNavigationInfo course(long courseId) {
        return new StudyItemNavigationInfo(
                TYPE_COURSE,
                courseId, null, null, null);
    }

    public static StudyItemNavigationInfo unit(long unitId) {
        return new StudyItemNavigationInfo(
                TYPE_UNIT,
                null, unitId, null, null);
    }

    public static StudyItemNavigationInfo lesson(long lessonId) {
        return new StudyItemNavigationInfo(
                TYPE_LESSON,
                null, null, lessonId, null);
    }

    public static StudyItemNavigationInfo lessonItem(long lessonItemId) {
        return new StudyItemNavigationInfo(
                TYPE_LESSON_ITEM,
                null, null, null, lessonItemId);
    }

    private StudyItemNavigationInfo(String type, Long courseId, Long unitId, Long lessonId, Long lessonItemId) {
        this.type = type;
        this.courseId = courseId;
        this.unitId = unitId;
        this.lessonId = lessonId;
        this.lessonItemId = lessonItemId;
    }

    public String getType() {
        return type;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public Long getLessonItemId() {
        return lessonItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyItemNavigationInfo that = (StudyItemNavigationInfo) o;
        return type.equals(that.type) &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(unitId, that.unitId) &&
                Objects.equals(lessonId, that.lessonId) &&
                Objects.equals(lessonItemId, that.lessonItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, courseId, unitId, lessonId, lessonItemId);
    }
}

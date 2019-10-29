package com.gravityray.basiclearningsystem.study;

public interface StudyService {

    CourseTree getCourseTreeByCourseId(String email, long courseId);

    CourseTree getCourseTreeByUnitId(String email, long unitId);

    CourseTree getCourseTreeByLessonId(String email, long lessonId);

    CourseTree getCourseTreeByLessonItemId(String email, long lessonItemId);

    StudyCourse getStudyCourse(long courseId);

    StudyUnit getStudyUnit(long unitId);

    StudyLesson getStudyLesson(long lessonId);

    StudyLessonItem getStudyLessonItem(String email, long lessonItemId);

    CompleteLessonItemInfo getCompleteLessonItemInfo(long lessonItemId);

    void completeLessonItem(String email, long lessonItemId);
}

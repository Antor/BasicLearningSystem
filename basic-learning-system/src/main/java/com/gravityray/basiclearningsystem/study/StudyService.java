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

    StudyItemNavigationInfo getNextStudyItemForCourse(long courseId);

    StudyItemNavigationInfo getPrevStudyItemForUnit(long unitId);

    StudyItemNavigationInfo getNextStudyItemForUnit(long unitId);

    StudyItemNavigationInfo getPrevStudyItemForLesson(long lessonId);

    StudyItemNavigationInfo getNextStudyItemForLesson(long lessonId);

    StudyItemNavigationInfo getPrevStudyItemForLessonItem(long lessonItemId);

    StudyItemNavigationInfo getNextStudyItemForLessonItem(long lessonItemId);
}

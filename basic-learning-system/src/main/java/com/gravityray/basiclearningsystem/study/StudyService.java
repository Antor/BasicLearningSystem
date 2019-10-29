package com.gravityray.basiclearningsystem.study;

public interface StudyService {

    CourseTree getCourseTreeByCourseId(long courseId);

    CourseTree getCourseTreeByUnitId(long unitId);

    CourseTree getCourseTreeByLessonId(long lessonId);

    CourseTree getCourseTreeByLessonItemId(long lessonItemId);

    StudyCourse getStudyCourse(long courseId);

    StudyUnit getStudyUnit(long unitId);

    StudyLesson getStudyLesson(long lessonId);

    StudyLessonItem getStudyLessonItem(long lessonItemId);
}

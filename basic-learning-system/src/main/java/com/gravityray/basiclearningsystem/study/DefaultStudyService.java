package com.gravityray.basiclearningsystem.study;

import com.gravityray.basiclearningsystem.studyitem.course.CourseDao;
import com.gravityray.basiclearningsystem.studyitem.lesson.LessonDao;
import com.gravityray.basiclearningsystem.studyitem.lesson.LessonEntity;
import com.gravityray.basiclearningsystem.studyitem.lessonitem.LessonItemDao;
import com.gravityray.basiclearningsystem.studyitem.lessonitem.LessonItemEntity;
import com.gravityray.basiclearningsystem.studyitem.unit.UnitDao;
import com.gravityray.basiclearningsystem.studyitem.unit.UnitEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultStudyService implements StudyService {

    private final CourseDao courseDao;
    private final UnitDao unitDao;
    private final LessonDao lessonDao;
    private final LessonItemDao lessonItemDao;

    public DefaultStudyService(
            CourseDao courseDao,
            UnitDao unitDao,
            LessonDao lessonDao,
            LessonItemDao lessonItemDao) {
        this.courseDao = courseDao;
        this.unitDao = unitDao;
        this.lessonDao = lessonDao;
        this.lessonItemDao = lessonItemDao;
    }

    @Override
    public CourseTree getCourseTreeByCourseId(long courseId) {
        return getCourseTree(courseId);
    }

    @Override
    public CourseTree getCourseTreeByUnitId(long unitId) {
        return unitDao.findById(unitId)
                .map(unitEntity -> getCourseTree(unitEntity.getCourseId()))
                .orElse(null);
    }

    @Override
    public CourseTree getCourseTreeByLessonId(long lessonId) {
        return lessonDao.findById(lessonId)
                .map(lessonEntity -> getCourseTree(lessonEntity.getUnit().getCourseId()))
                .orElse(null);
    }

    @Override
    public CourseTree getCourseTreeByLessonItemId(long lessonItemId) {
        return lessonItemDao.findById(lessonItemId)
                .map(lessonItemEntity -> getCourseTree(lessonItemEntity.getLesson().getUnit().getCourseId()))
                .orElse(null);
    }

    @Override
    public StudyCourse getStudyCourse(long courseId) {
        return courseDao.findById(courseId)
                .map(courseEntity -> {
                    StudyCourse course = new StudyCourse();
                    course.setTitle(courseEntity.getTitle());
                    course.setDescription(courseEntity.getDescription());
                    return course;
                })
                .orElse(null);
    }

    @Override
    public StudyUnit getStudyUnit(long unitId) {
        return unitDao.findById(unitId)
                .map(unitEntity -> {
                    StudyUnit unit = new StudyUnit();
                    unit.setTitle(unitEntity.getTitle());
                    unit.setDescription(unitEntity.getDescription());
                    return unit;
                })
                .orElse(null);
    }

    @Override
    public StudyLesson getStudyLesson(long lessonId) {
        return lessonDao.findById(lessonId)
                .map(lessonEntity -> {
                    StudyLesson lesson = new StudyLesson();
                    lesson.setTitle(lessonEntity.getTitle());
                    lesson.setDescription(lessonEntity.getDescription());
                    return lesson;
                })
                .orElse(null);
    }

    @Override
    public StudyLessonItem getStudyLessonItem(long lessonItemId) {
        return lessonItemDao.findById(lessonItemId)
                .map(lessonItemEntity -> {
                    StudyLessonItem lessonItem = new StudyLessonItem();
                    lessonItem.setTitle(lessonItemEntity.getTitle());
                    lessonItem.setContent(lessonItemEntity.getContent());
                    return lessonItem;
                })
                .orElse(null);
    }

    private CourseTree getCourseTree(long courseId) {
        return courseDao.findById(courseId)
                .map(courseEntity -> {
                    CourseTree course = new CourseTree();
                    course.setId(courseEntity.getId());
                    course.setTitle(courseEntity.getTitle());
                    course.setUnitList(toCourseTreeUnitList(courseEntity.getUnitList()));
                    return course;
                })
                .orElse(null);
    }

    private List<CourseTreeUnit> toCourseTreeUnitList(List<UnitEntity> unitEntityList) {
        List<CourseTreeUnit> unitList = new ArrayList<>();
        unitEntityList
                .forEach(unitEntity -> {
                    CourseTreeUnit unit = new CourseTreeUnit();
                    unit.setId(unitEntity.getId());
                    unit.setTitle(unitEntity.getTitle());
                    unit.setLessonList(toCourseTreeLessonList(unitEntity.getLessonList()));
                    unitList.add(unit);
                });
        return unitList;
    }

    private List<CourseTreeLesson> toCourseTreeLessonList(List<LessonEntity> lessonEntityList) {
        List<CourseTreeLesson> lessonList = new ArrayList<>();
        lessonEntityList
                .forEach(lessonEntity -> {
                    CourseTreeLesson lesson = new CourseTreeLesson();
                    lesson.setId(lessonEntity.getId());
                    lesson.setTitle(lessonEntity.getTitle());
                    lesson.setLessonItemList(toCourseTreeLessonItemList(lessonEntity.getLessonItems()));
                    lessonList.add(lesson);
                });
        return lessonList;
    }

    private List<CourseTreeLessonItem> toCourseTreeLessonItemList(List<LessonItemEntity> lessonItemEntityList) {
        List<CourseTreeLessonItem> lessonItemList = new ArrayList<>();
        lessonItemEntityList
                .forEach(lessonItemEntity -> {
                    CourseTreeLessonItem lessonItem = new CourseTreeLessonItem();
                    lessonItem.setId(lessonItemEntity.getId());
                    lessonItem.setTitle(lessonItemEntity.getTitle());
                    lessonItemList.add(lessonItem);
                });
        return lessonItemList;
    }
}

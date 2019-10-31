package com.gravityray.basiclearningsystem.study;

import com.gravityray.basiclearningsystem.studyitem.course.CourseDao;
import com.gravityray.basiclearningsystem.studyitem.course.CourseEntity;
import com.gravityray.basiclearningsystem.studyitem.lesson.LessonDao;
import com.gravityray.basiclearningsystem.studyitem.lesson.LessonEntity;
import com.gravityray.basiclearningsystem.studyitem.lessonitem.LessonItemDao;
import com.gravityray.basiclearningsystem.studyitem.lessonitem.LessonItemEntity;
import com.gravityray.basiclearningsystem.studyitem.unit.UnitDao;
import com.gravityray.basiclearningsystem.studyitem.unit.UnitEntity;
import com.gravityray.basiclearningsystem.user.UserDao;
import com.gravityray.basiclearningsystem.user.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultStudyService implements StudyService {

    private final CourseDao courseDao;
    private final UnitDao unitDao;
    private final LessonDao lessonDao;
    private final LessonItemDao lessonItemDao;
    private final UserDao userDao;

    public DefaultStudyService(
            CourseDao courseDao,
            UnitDao unitDao,
            LessonDao lessonDao,
            LessonItemDao lessonItemDao,
            UserDao userDao) {
        this.courseDao = courseDao;
        this.unitDao = unitDao;
        this.lessonDao = lessonDao;
        this.lessonItemDao = lessonItemDao;
        this.userDao = userDao;
    }

    @Override
    public CourseTree getCourseTreeByCourseId(String email, long courseId) {
        CourseTree courseTree = getCourseTree(email, courseId);
        courseTree.setCurrentStudyItem(true);
        return courseTree;
    }

    @Override
    public CourseTree getCourseTreeByUnitId(String email, long unitId) {
        CourseTree courseTree = unitDao.findById(unitId)
                .map(unitEntity -> getCourseTree(email, unitEntity.getCourseId()))
                .orElse(null);

        courseTree.getUnitList()
                .stream()
                .filter(unit -> unitId == unit.getId())
                .forEach(unit -> unit.setCurrentStudyItem(true));

        return courseTree;
    }

    @Override
    public CourseTree getCourseTreeByLessonId(String email, long lessonId) {
        CourseTree courseTree = lessonDao.findById(lessonId)
                .map(lessonEntity -> getCourseTree(email, lessonEntity.getUnit().getCourseId()))
                .orElse(null);

        courseTree.getUnitList()
                .stream()
                .flatMap(unit -> unit.getLessonList().stream())
                .filter(lesson -> lessonId == lesson.getId())
                .forEach(unit -> unit.setCurrentStudyItem(true));

        return courseTree;
    }

    @Override
    public CourseTree getCourseTreeByLessonItemId(String email, long lessonItemId) {
        CourseTree courseTree =  lessonItemDao.findById(lessonItemId)
                .map(lessonItemEntity -> getCourseTree(email, lessonItemEntity.getLesson().getUnit().getCourseId()))
                .orElse(null);

        courseTree.getUnitList()
                .stream()
                .flatMap(unit -> unit.getLessonList().stream())
                .flatMap(lesson -> lesson.getLessonItemList().stream())
                .filter(lessonItem -> lessonItemId == lessonItem.getId())
                .forEach(unit -> unit.setCurrentStudyItem(true));

        return courseTree;
    }

    @Override
    public StudyCourse getStudyCourse(long courseId) {
        return courseDao.findById(courseId)
                .map(courseEntity -> {
                    StudyCourse course = new StudyCourse();
                    course.setId(courseEntity.getId());
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
                    unit.setId(unitEntity.getId());
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
                    lesson.setId(lessonEntity.getId());
                    lesson.setTitle(lessonEntity.getTitle());
                    lesson.setDescription(lessonEntity.getDescription());
                    return lesson;
                })
                .orElse(null);
    }

    @Override
    public StudyLessonItem getStudyLessonItem(String email, long lessonItemId) {
        UserEntity userEntity = userDao.findUserByEmail(email);
        return lessonItemDao.findById(lessonItemId)
                .map(lessonItemEntity -> {
                    StudyLessonItem lessonItem = new StudyLessonItem();
                    lessonItem.setId(lessonItemEntity.getId());
                    lessonItem.setTitle(lessonItemEntity.getTitle());
                    lessonItem.setContent(lessonItemEntity.getContent());
                    lessonItem.setComplete(userEntity.getCompletedLessonItemSet().contains(lessonItemEntity));
                    return lessonItem;
                })
                .orElse(null);
    }

    @Override
    public CompleteLessonItemInfo getCompleteLessonItemInfo(long lessonItemId) {
        return lessonItemDao.findById(lessonItemId)
                .map(lessonItemEntity -> {
                    CompleteLessonItemInfo info = new CompleteLessonItemInfo();
                    info.setId(lessonItemEntity.getId());
                    info.setTitle(lessonItemEntity.getTitle());
                    return info;
                })
                .orElse(null);
    }

    @Transactional
    @Override
    public void completeLessonItem(String email, long lessonItemId) {
        UserEntity userEntity = userDao.findUserByEmail(email);
        LessonItemEntity lessonItemEntity = lessonItemDao.findById(lessonItemId)
                .orElse(null);
        userEntity.getCompletedLessonItemSet().add(lessonItemEntity);
    }

    @Override
    public StudyItemNavigationInfo getNextStudyItemForCourse(long courseId) {
        return getNextStudyItem(
                getFlattenCourseNavigationTree(courseId),
                StudyItemNavigationInfo.course(courseId));
    }

    @Override
    public StudyItemNavigationInfo getPrevStudyItemForUnit(long unitId) {
        long courseId = unitDao.findById(unitId).orElse(null).getCourseId();
        return getPrevStudyItem(
                getFlattenCourseNavigationTree(courseId),
                StudyItemNavigationInfo.unit(unitId));
    }

    @Override
    public StudyItemNavigationInfo getNextStudyItemForUnit(long unitId) {
        long courseId = unitDao.findById(unitId).orElse(null).getCourseId();
        return getNextStudyItem(
                getFlattenCourseNavigationTree(courseId),
                StudyItemNavigationInfo.unit(unitId));
    }

    @Override
    public StudyItemNavigationInfo getPrevStudyItemForLesson(long lessonId) {
        long courseId = lessonDao.findById(lessonId)
                .orElse(null)
                .getUnit()
                .getCourseId();
        return getPrevStudyItem(
                getFlattenCourseNavigationTree(courseId),
                StudyItemNavigationInfo.lesson(lessonId));
    }

    @Override
    public StudyItemNavigationInfo getNextStudyItemForLesson(long lessonId) {
        long courseId = lessonDao.findById(lessonId)
                .orElse(null)
                .getUnit()
                .getCourseId();
        return getNextStudyItem(
                getFlattenCourseNavigationTree(courseId),
                StudyItemNavigationInfo.lesson(lessonId));
    }

    @Override
    public StudyItemNavigationInfo getPrevStudyItemForLessonItem(long lessonItemId) {
        long courseId = lessonItemDao.findById(lessonItemId)
                .orElse(null)
                .getLesson()
                .getUnit()
                .getCourseId();
        return getPrevStudyItem(
                getFlattenCourseNavigationTree(courseId),
                StudyItemNavigationInfo.lessonItem(lessonItemId));
    }

    @Override
    public StudyItemNavigationInfo getNextStudyItemForLessonItem(long lessonItemId) {
        long courseId = lessonItemDao.findById(lessonItemId)
                .orElse(null)
                .getLesson()
                .getUnit()
                .getCourseId();
        return getNextStudyItem(
                getFlattenCourseNavigationTree(courseId),
                StudyItemNavigationInfo.lessonItem(lessonItemId));
    }

    private CourseTree getCourseTree(String email, long courseId) {
        return courseDao.findById(courseId)
                .map(courseEntity -> {
                    CourseTree course = new CourseTree();
                    course.setId(courseEntity.getId());
                    course.setTitle(courseEntity.getTitle());

                    List<CourseTreeUnit> unitList = toCourseTreeUnitList(email, courseEntity.getUnitList());
                    course.setUnitList(unitList);

                    course.setLessonItemCountCompleted(
                            unitList.stream()
                                    .mapToLong(CourseTreeUnit::getLessonItemCountCompleted)
                                    .sum());
                    course.setLessonItemCountTotal(
                            unitList.stream()
                                    .mapToLong(CourseTreeUnit::getLessonItemCountTotal)
                                    .sum());

                    return course;
                })
                .orElse(null);
    }

    private List<CourseTreeUnit> toCourseTreeUnitList(String email, List<UnitEntity> unitEntityList) {
        List<CourseTreeUnit> unitList = new ArrayList<>();
        unitEntityList
                .forEach(unitEntity -> {
                    CourseTreeUnit unit = new CourseTreeUnit();
                    unit.setId(unitEntity.getId());
                    unit.setTitle(unitEntity.getTitle());

                    List<CourseTreeLesson> lessonList =
                            toCourseTreeLessonList(email, unitEntity.getLessonList());
                    unit.setLessonList(lessonList);

                    unit.setLessonItemCountCompleted(
                            lessonList.stream()
                                    .mapToLong(CourseTreeLesson::getLessonItemCountCompleted)
                                    .sum());
                    unit.setLessonItemCountTotal(
                            lessonList.stream()
                                    .mapToLong(CourseTreeLesson::getLessonItemCountTotal)
                                    .sum());

                    unitList.add(unit);
                });
        return unitList;
    }

    private List<CourseTreeLesson> toCourseTreeLessonList(String email, List<LessonEntity> lessonEntityList) {
        List<CourseTreeLesson> lessonList = new ArrayList<>();
        lessonEntityList
                .forEach(lessonEntity -> {
                    CourseTreeLesson lesson = new CourseTreeLesson();
                    lesson.setId(lessonEntity.getId());
                    lesson.setTitle(lessonEntity.getTitle());

                    List<CourseTreeLessonItem> lessonItemList =
                            toCourseTreeLessonItemList(email, lessonEntity.getLessonItems());
                    lesson.setLessonItemList(lessonItemList);

                    lesson.setLessonItemCountCompleted(
                            lessonItemList.stream()
                                    .filter(CourseTreeLessonItem::isComplete)
                                    .count());
                    lesson.setLessonItemCountTotal(lessonItemList.size());

                    lessonList.add(lesson);
                });
        return lessonList;
    }

    private List<CourseTreeLessonItem> toCourseTreeLessonItemList(String email, List<LessonItemEntity> lessonItemEntityList) {
        UserEntity userEntity = userDao.findUserByEmail(email);
        List<CourseTreeLessonItem> lessonItemList = new ArrayList<>();
        lessonItemEntityList
                .forEach(lessonItemEntity -> {
                    CourseTreeLessonItem lessonItem = new CourseTreeLessonItem();
                    lessonItem.setId(lessonItemEntity.getId());
                    lessonItem.setTitle(lessonItemEntity.getTitle());
                    lessonItem.setComplete(userEntity.getCompletedLessonItemSet().contains(lessonItemEntity));
                    lessonItemList.add(lessonItem);
                });
        return lessonItemList;
    }

    private List<StudyItemNavigationInfo> getFlattenCourseNavigationTree(long courseId) {
        CourseEntity course = courseDao.findById(courseId).orElse(null);

        List<StudyItemNavigationInfo> navigationList = new ArrayList<>();
        navigationList.add(StudyItemNavigationInfo.course(courseId));
        course.getUnitList()
                .forEach(unit -> {
                    navigationList.addAll(getFlattenUnitNavigationTree(unit));
                });
        return navigationList;
    }

    private List<StudyItemNavigationInfo> getFlattenUnitNavigationTree(UnitEntity unit) {
        List<StudyItemNavigationInfo> navigationList = new ArrayList<>();
        navigationList.add(StudyItemNavigationInfo.unit(unit.getId()));
        unit.getLessonList()
                .forEach(lesson -> {
                    navigationList.addAll(getFlattenLessonNavigationTree(lesson));
                });
        return navigationList;
    }

    private List<StudyItemNavigationInfo> getFlattenLessonNavigationTree(LessonEntity lesson) {
        List<StudyItemNavigationInfo> navigationList = new ArrayList<>();
        navigationList.add(StudyItemNavigationInfo.lesson(lesson.getId()));
        lesson.getLessonItems()
                .forEach(lessonItem -> {
                    navigationList.add(StudyItemNavigationInfo.lessonItem(lessonItem.getId()));
                });
        return navigationList;
    }

    private StudyItemNavigationInfo getPrevStudyItem(
            List<StudyItemNavigationInfo> navigationList,
            StudyItemNavigationInfo currentItem) {

        int currentPosition = navigationList.indexOf(currentItem);
        StudyItemNavigationInfo nextItem;
        if (currentPosition == 0) {
            nextItem = currentItem;

        } else {
            nextItem = navigationList.get(currentPosition - 1);
        }
        return nextItem;
    }

    private StudyItemNavigationInfo getNextStudyItem(
            List<StudyItemNavigationInfo> navigationList,
            StudyItemNavigationInfo currentItem) {

        int currentPosition = navigationList.indexOf(currentItem);
        StudyItemNavigationInfo nextItem;
        if (currentPosition == navigationList.size() - 1) {
            nextItem = currentItem;

        } else {
            nextItem = navigationList.get(currentPosition + 1);
        }
        return nextItem;
    }
}

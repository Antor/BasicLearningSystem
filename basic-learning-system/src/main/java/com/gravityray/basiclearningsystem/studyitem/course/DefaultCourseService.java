package com.gravityray.basiclearningsystem.studyitem.course;

import com.gravityray.basiclearningsystem.adminpanel.course.CourseActiveToggleInfo;
import com.gravityray.basiclearningsystem.adminpanel.course.CreateCourseForm;
import com.gravityray.basiclearningsystem.adminpanel.course.DeleteCourseInfo;
import com.gravityray.basiclearningsystem.adminpanel.course.EditCourseForm;
import com.gravityray.basiclearningsystem.studyitem.unit.UnitEntity;
import com.gravityray.basiclearningsystem.user.UserDao;
import com.gravityray.basiclearningsystem.user.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultCourseService implements CourseService {

    private final CourseDao courseDao;
    private final UserDao userDao;
    private final Validator validator;

    public DefaultCourseService(
            CourseDao courseDao,
            UserDao userDao,
            Validator validator) {
        this.courseDao = courseDao;
        this.userDao = userDao;
        this.validator = validator;
    }

    @Override
    public List<CourseEntity> getCourses(boolean onlyActive) {
        Iterable<CourseEntity> courseIterable = onlyActive
                ? courseDao.getActiveCourses()
                : courseDao.findAll();

        List<CourseEntity> courseList = new ArrayList<>();
        courseIterable.forEach(courseList::add);
        return courseList;
    }

    @Override
    public CourseEntity getCourse(long id) {
        return courseDao.findById(id).orElse(null);
    }

    @Override
    public EditCourseForm getEditCourseForm(long id) {
        return courseDao.findById(id)
                .map(courseEntity -> {
                    EditCourseForm form = new EditCourseForm();
                    form.setId(courseEntity.getId());
                    form.setTitle(courseEntity.getTitle());
                    form.setDescription(courseEntity.getDescription());
                    return form;
                })
                .orElse(null);
    }

    @Override
    public DeleteCourseInfo getDeleteCourseInfo(long id) {
        return courseDao.findById(id)
                .map(courseEntity -> {
                    DeleteCourseInfo course = new DeleteCourseInfo();
                    course.setId(courseEntity.getId());
                    course.setTitle(courseEntity.getTitle());
                    return course;
                })
                .orElse(null);
    }

    @Override
    public CourseActiveToggleInfo getCourseActiveToggleInfo(Long id) {
        return courseDao.findById(id)
                .map(courseEntity -> {
                    CourseActiveToggleInfo course = new CourseActiveToggleInfo();
                    course.setId(courseEntity.getId());
                    course.setTitle(courseEntity.getTitle());
                    course.setActive(courseEntity.isActive());
                    return course;
                })
                .orElse(null);
    }

    @Transactional
    @Override
    public void createCourse(CreateCourseForm course)
            throws CreateCourseFormNotValidException {
        Set<ConstraintViolation<CreateCourseForm>> constraintViolationSet =
                validator.validate(course);
        if (!constraintViolationSet.isEmpty()) {
            throw new CreateCourseFormNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setTitle(course.getTitle());
        courseEntity.setDescription(course.getDescription());
        courseEntity.setActive(false);
        courseDao.save(courseEntity);
    }

    @Override
    public long addCourse(CourseEntity courseEntity) {
        return courseDao.save(courseEntity).getId();
    }

    @Override
    public void updateCourse(CourseEntity courseEntity) {
        courseDao.save(courseEntity);
    }

    @Transactional
    @Override
    public void updateCourse(EditCourseForm editCourseForm)
            throws CourseNotFoundException, EditCourseFormNotValidException {
        Set<ConstraintViolation<EditCourseForm>> constraintViolationSet =
                validator.validate(editCourseForm);
        if (!constraintViolationSet.isEmpty()) {
            throw new EditCourseFormNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }

        CourseEntity courseEntity = courseDao.findById(editCourseForm.getId())
                .orElse(null);
        if (courseEntity == null) {
            throw new CourseNotFoundException();
        }
        courseEntity.setTitle(editCourseForm.getTitle());
        courseEntity.setDescription(editCourseForm.getDescription());
    }

    @Transactional
    @Override
    public void toggleCourseActive(Long id) {
        courseDao.findById(id)
                .ifPresent(course -> course.setActive(!course.isActive()));
    }

    @Transactional
    @Override
    public void activateCourse(long courseId) {
        courseDao.findById(courseId)
                .ifPresent(courseEntity -> courseEntity.setActive(true));
    }

    @Transactional
    @Override
    public void deactivateCourse(long courseId) {
        courseDao.findById(courseId)
                .ifPresent(courseEntity -> courseEntity.setActive(true));
    }

    @Transactional
    @Override
    public void deleteCourse(long id) {
        courseDao.deleteById(id);
    }

    @Transactional
    @Override
    public void enrolUserToCourse(long courseId, String email) {
        UserEntity userEntity = userDao.findUserByEmail(email);
        CourseEntity courseEntity = courseDao.findById(courseId).orElse(null);

        boolean hasCourse = userEntity.getCourseList()
                .stream()
                .anyMatch(userCourseEntity -> courseEntity.getId().equals(userCourseEntity.getId()));
        if (!hasCourse) {
            userEntity.getCourseList().add(courseEntity);
        }
    }

    @Transactional
    @Override
    public void unenrolUserFromCourse(long courseId, String email) {
        UserEntity userEntity = userDao.findUserByEmail(email);
        CourseEntity courseEntity = courseDao.findById(courseId).orElse(null);

        boolean hasCourse = userEntity.getCourseList()
                .stream()
                .anyMatch(userCourseEntity -> courseEntity.getId().equals(userCourseEntity.getId()));
        if (hasCourse) {
            userEntity.getCourseList().remove(courseEntity);
        }
    }

    @Override
    public CourseItemListInfo getActiveCourseItemListInfo(String email) {
        UserEntity userEntity = userDao.findUserByEmail(email);

        List<CourseItemInfo> courseList = new ArrayList<>();
        courseDao.getActiveCourses()
                .forEach(courseEntity -> {
                    CourseItemInfo item = new CourseItemInfo();
                    item.setId(courseEntity.getId());
                    item.setTitle(courseEntity.getTitle());
                    item.setDescription(courseEntity.getDescription());

                    item.setEnrolled(false);
                    if (userEntity != null) {
                        for (CourseEntity userCourseEntity : userEntity.getCourseList()) {
                            if (courseEntity.getId().equals(userCourseEntity.getId())) {
                                item.setEnrolled(true);
                                break;
                            }
                        }
                    }

                    courseList.add(item);
                });

        CourseItemListInfo info = new CourseItemListInfo();
        info.setUserAuthenticated(userEntity != null);
        info.setCourseList(courseList);
        return info;
    }

    @Override
    public CourseItemInfo getCourseItemInfo(long courseId, String email) {
        CourseEntity courseEntity = courseDao.findById(courseId).orElse(null);

        CourseItemInfo item = new CourseItemInfo();
        item.setId(courseEntity.getId());
        item.setTitle(courseEntity.getTitle());
        item.setDescription(courseEntity.getDescription());

        item.setEnrolled(false);
        UserEntity userEntity = userDao.findUserByEmail(email);
        for (CourseEntity userCourse : userEntity.getCourseList()) {
            if (userCourse.getId().equals(courseId)) {
                item.setEnrolled(true);
            }
        }

        return item;
    }
}

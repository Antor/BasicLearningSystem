package com.gravityray.basiclearningsystem.enrolment;

import com.gravityray.basiclearningsystem.studyitem.course.CourseDao;
import com.gravityray.basiclearningsystem.studyitem.course.CourseEntity;
import com.gravityray.basiclearningsystem.user.UserDao;
import com.gravityray.basiclearningsystem.user.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultEnrolmentService implements EnrolmentService {

    private final CourseDao courseDao;
    private final UserDao userDao;

    public DefaultEnrolmentService(CourseDao courseDao, UserDao userDao) {
        this.courseDao = courseDao;
        this.userDao = userDao;
    }

    @Override
    public CourseEnrolmentInfo getCourseEnrolmentInfo(long courseId, String email) {
        CourseEntity courseEntity = courseDao.findById(courseId).orElse(null);

        CourseEnrolmentInfo courseEnrolmentInfo = new CourseEnrolmentInfo();
        courseEnrolmentInfo.setId(courseEntity.getId());
        courseEnrolmentInfo.setTitle(courseEntity.getTitle());
        courseEnrolmentInfo.setDescription(courseEntity.getDescription());

        courseEnrolmentInfo.setEnrolled(false);
        UserEntity userEntity = userDao.findUserByEmail(email);
        for (CourseEntity userCourse : userEntity.getCourseList()) {
            if (userCourse.getId().equals(courseId)) {
                courseEnrolmentInfo.setEnrolled(true);
            }
        }

        return courseEnrolmentInfo;
    }

    @Transactional
    @Override
    public void enrolCourse(long courseId, String email) {
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
    public void leaveCourse(long courseId, String email) {
        UserEntity userEntity = userDao.findUserByEmail(email);
        CourseEntity courseEntity = courseDao.findById(courseId).orElse(null);

        boolean hasCourse = userEntity.getCourseList()
                .stream()
                .anyMatch(userCourseEntity -> courseEntity.getId().equals(userCourseEntity.getId()));
        if (hasCourse) {
            userEntity.getCourseList().remove(courseEntity);
        }
    }
}

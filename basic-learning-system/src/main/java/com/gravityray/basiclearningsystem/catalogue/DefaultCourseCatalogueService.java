package com.gravityray.basiclearningsystem.catalogue;

import com.gravityray.basiclearningsystem.studyitem.course.CourseDao;
import com.gravityray.basiclearningsystem.studyitem.course.CourseEntity;
import com.gravityray.basiclearningsystem.user.UserDao;
import com.gravityray.basiclearningsystem.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultCourseCatalogueService implements CourseCatalogueService {

    private final UserDao userDao;
    private final CourseDao courseDao;

    public DefaultCourseCatalogueService(UserDao userDao, CourseDao courseDao) {
        this.userDao = userDao;
        this.courseDao = courseDao;
    }

    @Override
    public CourseCatalogue getCourseCatalogue(String userEmail) {
        UserEntity userEntity = userDao.findUserByEmail(userEmail);

        List<CourseCatalogueItem> courseList = new ArrayList<>();
        courseDao.getActiveCourses()
                .forEach(courseEntity -> {
                    CourseCatalogueItem item = new CourseCatalogueItem();
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

        CourseCatalogue info = new CourseCatalogue();
        info.setUserAuthenticated(userEntity != null);
        info.setCourseList(courseList);
        return info;
    }
}

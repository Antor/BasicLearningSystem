package com.gravityray.basiclearningsystem.enrolment;


public interface EnrolmentService {

    CourseEnrolmentInfo getCourseEnrolmentInfo(long courseId, String email);

    void enrolCourse(long courseId, String email);

    void leaveCourse(long courseId, String email);
}

package com.gravityray.basiclearningsystem.user;

import com.gravityray.basiclearningsystem.studyitem.course.CourseEntity;
import com.gravityray.basiclearningsystem.studyitem.lessonitem.LessonItemEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_role")
    private String role;

    @ManyToMany
    @JoinTable(
            name = "users_courses_enrolment",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<CourseEntity> courseList;

    @ManyToMany
    @JoinTable(
            name = "users_completed_lesson_items",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_item_id")
    )
    private Set<LessonItemEntity> completedLessonItemSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<CourseEntity> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseEntity> courseList) {
        this.courseList = courseList;
    }

    public Set<LessonItemEntity> getCompletedLessonItemSet() {
        return completedLessonItemSet;
    }

    public void setCompletedLessonItemSet(Set<LessonItemEntity> completedLessonItemSet) {
        this.completedLessonItemSet = completedLessonItemSet;
    }
}

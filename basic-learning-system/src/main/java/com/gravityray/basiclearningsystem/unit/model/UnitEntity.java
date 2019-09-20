package com.gravityray.basiclearningsystem.unit.model;

import com.gravityray.basiclearningsystem.course.model.CourseEntity;
import com.gravityray.basiclearningsystem.lesson.model.LessonEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "units")
public class UnitEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "ordinal")
    private Integer ordinal;

    @Column(name = "course_id")
    private Long courseId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private CourseEntity course;

    @OneToMany(mappedBy = "unit")
    private List<LessonEntity> lessonList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public List<LessonEntity> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<LessonEntity> lessonList) {
        this.lessonList = lessonList;
    }
}

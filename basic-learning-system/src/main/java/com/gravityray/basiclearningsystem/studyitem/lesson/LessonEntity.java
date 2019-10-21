package com.gravityray.basiclearningsystem.studyitem.lesson;

import com.gravityray.basiclearningsystem.studyitem.lessonitem.LessonItemEntity;
import com.gravityray.basiclearningsystem.studyitem.unit.UnitEntity;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("unused")
@Entity(name = "lessons")
public class LessonEntity {

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

    @Column(name = "unit_id")
    private Long unitId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unit_id", insertable = false, updatable = false)
    private UnitEntity unit;

    @OneToMany(mappedBy = "lesson")
    private List<LessonItemEntity> lessonItems;

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

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public UnitEntity getUnit() {
        return unit;
    }

    public void setUnit(UnitEntity unit) {
        this.unit = unit;
    }

    public List<LessonItemEntity> getLessonItems() {
        return lessonItems;
    }

    public void setLessonItems(List<LessonItemEntity> lessonItems) {
        this.lessonItems = lessonItems;
    }
}

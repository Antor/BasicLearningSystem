package com.gravityray.basiclearningsystem.studyitem.course;

import com.gravityray.basiclearningsystem.studyitem.unit.UnitEntity;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("unused")
@Entity(name = "courses")
public class CourseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "course")
    private List<UnitEntity> unitList;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<UnitEntity> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<UnitEntity> unitList) {
        this.unitList = unitList;
    }
}

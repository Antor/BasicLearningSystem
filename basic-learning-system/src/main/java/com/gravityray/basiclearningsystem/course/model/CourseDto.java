package com.gravityray.basiclearningsystem.course.model;

import com.gravityray.basiclearningsystem.unit.model.UnitDto;

import java.util.List;

@SuppressWarnings("unused")
public class CourseDto {

    private Long id;
    private String title;
    private String description;
    private boolean active;
    private List<UnitDto> units;

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

    public List<UnitDto> getUnits() {
        return units;
    }

    public void setUnits(List<UnitDto> units) {
        this.units = units;
    }
}

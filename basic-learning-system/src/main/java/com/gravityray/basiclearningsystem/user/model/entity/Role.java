package com.gravityray.basiclearningsystem.user.model.entity;

public class Role {

    public static final String ID_ADMIN = "admin";
    public static final String ID_STUDENT = "student";

    private String id;
    private String name;

    public Role() {
    }

    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

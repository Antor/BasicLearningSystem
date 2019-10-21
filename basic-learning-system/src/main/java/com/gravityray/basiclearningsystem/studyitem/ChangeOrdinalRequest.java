package com.gravityray.basiclearningsystem.studyitem;

@SuppressWarnings("unused")
public class ChangeOrdinalRequest {

    private Long id;
    private Integer delta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDelta() {
        return delta;
    }

    public void setDelta(Integer delta) {
        this.delta = delta;
    }
}

package com.example.georgebg.unifornews.event;

public class NoticiaClicadaEvent {
    private Integer position;

    public NoticiaClicadaEvent(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position){
        this.position = position;
    }

}


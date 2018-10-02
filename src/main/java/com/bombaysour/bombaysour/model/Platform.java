package com.bombaysour.bombaysour.model;

import javax.persistence.Entity;

@Entity
public class Platform extends BaseEntity<Platform>{

    private String image;

    // TODO: 02/10/2018  add field for texts


    public Platform() {

    }

    public String getImage() {
        return image;
    }

    public Platform setImage(String image) {
        this.image = image;
        return this;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "image='" + image + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}

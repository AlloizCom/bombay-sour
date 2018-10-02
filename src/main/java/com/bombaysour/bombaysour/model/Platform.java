package com.bombaysour.bombaysour.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Platform extends BaseEntity<Platform>{

    private String image;

    @Column(columnDefinition = "LONGTEXT")
    private String text;

    public Platform() {

    }

    public String getImage() {
        return image;
    }

    public Platform setImage(String image) {
        this.image = image;
        return this;
    }

    public String getText() {
        return text;
    }

    public Platform setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "image='" + image + '\'' +
                ", text='" + text + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}

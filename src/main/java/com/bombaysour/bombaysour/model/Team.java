package com.bombaysour.bombaysour.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Team extends BaseEntity<Team>{

    private String name;
    @Column(columnDefinition = "LONGTEXT")
    private String image;

    @Column(columnDefinition = "LONGTEXT")
    private String biography;

    public Team() {
    }

    public String getName() {
        return name;
    }

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Team setImage(String image) {
        this.image = image;
        return this;
    }

    public String getBiography() {
        return biography;
    }

    public Team setBiography(String biography) {
        this.biography = biography;
        return this;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", biography='" + biography + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}

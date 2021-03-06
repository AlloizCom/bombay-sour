package com.bombaysour.bombaysour.dto;

import com.bombaysour.bombaysour.dto.utils.annotations.Dto;

@Dto
public class TeamDto {

    protected Long id;
    protected Boolean available;
    protected String name;
    protected String image;
    private String biography;


    public TeamDto() {
    }

    public Long getId() {
        return id;
    }

    public TeamDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public TeamDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public String getName() {
        return name;
    }

    public TeamDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public TeamDto setImage(String image) {
        this.image = image;
        return this;
    }

    public String getBiography() {
        return biography;
    }

    public TeamDto setBiography(String biography) {
        this.biography = biography;
        return this;
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "id=" + id +
                ", available=" + available +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", biography='" + biography + '\'' +
                '}';
    }
}

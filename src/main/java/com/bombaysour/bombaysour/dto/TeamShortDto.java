package com.bombaysour.bombaysour.dto;

import com.bombaysour.bombaysour.dto.utils.annotations.Dto;

@Dto
public class TeamShortDto {

    protected Long id;
    protected Boolean available;
    protected String name;
//    protected String image;

    public TeamShortDto() {
    }

    public Long getId() {
        return id;
    }

    public TeamShortDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public TeamShortDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public String getName() {
        return name;
    }

    public TeamShortDto setName(String name) {
        this.name = name;
        return this;
    }

//    public String getImage() {
//        return image;
//    }
//
//    public TeamShortDto setImage(String image) {
//        this.image = image;
//        return this;
//    }

    @Override
    public String toString() {
        return "TeamShortDto{" +
                "id=" + id +
                ", available=" + available +
                ", name='" + name + '\'' +
//                ", image='" + image + '\'' +
                '}';
    }
}

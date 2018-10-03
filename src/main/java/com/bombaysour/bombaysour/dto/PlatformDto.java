package com.bombaysour.bombaysour.dto;

import com.bombaysour.bombaysour.dto.utils.annotations.Dto;

@Dto
public class PlatformDto {

    protected Long id;
    protected Boolean available;
//    protected String image;
    protected String text;

    public PlatformDto() {
    }

    public Long getId() {
        return id;
    }

    public PlatformDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public PlatformDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

//    public String getImage() {
//        return image;
//    }
//
//    public PlatformDto setImage(String image) {
//        this.image = image;
//        return this;
//    }

    public String getText() {
        return text;
    }

    public PlatformDto setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        return "PlatformDto{" +
                "id=" + id +
                ", available=" + available +
//                ", image='" + image + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

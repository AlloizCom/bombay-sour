package com.bombaysour.bombaysour.dto;

import com.bombaysour.bombaysour.dto.utils.annotations.Dto;

@Dto
public class PlatformShortDto {

    protected Long id;
    protected Boolean available;
//    protected String image;
    protected String text;

    public PlatformShortDto() {
    }

    public Long getId() {
        return id;
    }

    public PlatformShortDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public PlatformShortDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

//    public String getImage() {
//        return image;
//    }
//
//    public PlatformShortDto setImage(String image) {
//        this.image = image;
//        return this;
//    }

    public String getText() {
        return text;
    }

    public PlatformShortDto setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        return "PlatformShortDto{" +
                "id=" + id +
                ", available=" + available +
//                ", image='" + image + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

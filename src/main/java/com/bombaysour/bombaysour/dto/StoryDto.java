package com.bombaysour.bombaysour.dto;

import com.bombaysour.bombaysour.dto.utils.annotations.Dto;

@Dto
public class StoryDto {

    protected Long id;
    protected Boolean available;
    protected String name;
    protected String videoUrl;

    public StoryDto() {
    }

    public Long getId() {
        return id;
    }

    public StoryDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public StoryDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public String getName() {
        return name;
    }

    public StoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public StoryDto setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    @Override
    public String toString() {
        return "StoryDto{" +
                "id=" + id +
                ", available=" + available +
                ", name='" + name + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
}

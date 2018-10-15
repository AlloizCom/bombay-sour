package com.bombaysour.bombaysour.model;

import javax.persistence.Entity;

@Entity
public class Story extends BaseEntity<Story>{

    private String name;
    private String videoUrl;
    private String poster;

    public Story() {
    }

    public String getName() {
        return name;
    }

    public Story setName(String name) {
        this.name = name;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Story setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    @Override
    public String toString() {
        return "Story{" +
                "name='" + name + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }

    public String getPoster() {
        return poster;
    }

    public Story setPoster(String poster) {
        this.poster = poster;
        return this;
    }
}

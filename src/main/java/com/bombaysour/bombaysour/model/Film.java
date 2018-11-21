package com.bombaysour.bombaysour.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Film extends BaseEntity<Film>{

    private String filmTitle;
    private String videoUrl;
    private String director;
    @Column(columnDefinition = "LONGTEXT")
    private String poster;

    public Film() {
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public Film setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Film setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public Film setDirector(String director) {
        this.director = director;
        return this;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmTitle='" + filmTitle + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", director='" + director + '\'' +
                ", poster='" + poster + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }

    public String getPoster() {
        return poster;
    }

    public Film setPoster(String poster) {
        this.poster = poster;
        return this;
    }
}

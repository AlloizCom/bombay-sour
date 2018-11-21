package com.bombaysour.bombaysour.dto;

import com.bombaysour.bombaysour.dto.utils.annotations.Dto;

@Dto
public class FilmDto {

    protected Long id;
    protected Boolean available;
    protected String filmTitle;
    protected String videoUrl;
    protected String director;


    public FilmDto() {
    }

    public Long getId() {
        return id;
    }

    public FilmDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public FilmDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public FilmDto setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public FilmDto setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public FilmDto setDirector(String director) {
        this.director = director;
        return this;
    }


    @Override
    public String toString() {
        return "FilmDto{" +
                "id=" + id +
                ", available=" + available +
                ", filmTitle='" + filmTitle + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}

package com.bombaysour.bombaysour.dto;

import com.bombaysour.bombaysour.dto.utils.annotations.Dto;

@Dto
public class CommunityDto {

    protected Long id;
    protected Boolean available;
    private String articleTitle;
    private String text;

    public CommunityDto() {
    }

    public Long getId() {
        return id;
    }

    public CommunityDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public CommunityDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public CommunityDto setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
        return this;
    }

    public String getText() {
        return text;
    }

    public CommunityDto setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        return "CommunityDto{" +
                "id=" + id +
                ", available=" + available +
                ", articleTitle='" + articleTitle + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

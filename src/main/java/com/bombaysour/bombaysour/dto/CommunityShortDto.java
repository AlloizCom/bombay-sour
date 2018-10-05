package com.bombaysour.bombaysour.dto;

import com.bombaysour.bombaysour.dto.utils.annotations.Dto;

@Dto
public class CommunityShortDto {

    protected Long id;
    protected Boolean available;
    protected String articleTitle;
    protected String text;

    public CommunityShortDto() {
    }

    public Long getId() {
        return id;
    }

    public CommunityShortDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public CommunityShortDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public CommunityShortDto setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
        return this;
    }

    public String getText() {
        return text;
    }

    public CommunityShortDto setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        return "CommunityShortDto{" +
                "id=" + id +
                ", available=" + available +
                ", articleTitle='" + articleTitle + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

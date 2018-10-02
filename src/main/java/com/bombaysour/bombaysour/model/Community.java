package com.bombaysour.bombaysour.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Community extends BaseEntity<Community>{

    private String articleTitle;

    @Column(columnDefinition = "LONGTEXT")
    private String text;

    public Community() {
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public Community setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
        return this;
    }

    public String getText() {
        return text;
    }

    public Community setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        return "Community{" +
                "articleTitle='" + articleTitle + '\'' +
                ", text='" + text + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}

package com.bombaysour.bombaysour.model;

import javax.persistence.Entity;

@Entity
public class Community extends BaseEntity<Community>{

    private String articleTitle;

    // TODO: 02/10/2018  add field for texts

    public Community() {
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public Community setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
        return this;
    }

    @Override
    public String toString() {
        return "Community{" +
                "articleTitle='" + articleTitle + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}

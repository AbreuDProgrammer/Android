package com.example.myapplicationandroid2023.db;

public class ArticleTag {

    int id;
    int article_id;
    int tag_id;

    public ArticleTag(int article_id, int tag_id) {
        this.article_id = article_id;
        this.tag_id = tag_id;
    }
}

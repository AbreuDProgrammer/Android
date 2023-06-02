package com.example.myapplicationandroid2023.db;

public class Article {

    int id;
    String title;
    String url;
    int status;
    String created_at;

    public Article() {}
    public Article(String title, String url, int status) {
        this.title = title;
        this.url = url;
        this.status = status;
    }

}

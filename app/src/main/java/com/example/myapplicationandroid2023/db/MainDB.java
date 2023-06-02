package com.example.myapplicationandroid2023.db;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationandroid2023.R;

import java.util.List;

public class MainDB extends Activity {
    RecyclerView rv;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindb);

        rv = (RecyclerView) findViewById(R.id.rvdb);
        db = new DatabaseHelper(getApplicationContext());
        rv.setLayoutManager(new LinearLayoutManager(this));

        Tag tPhp = db.createTag("PHP");
        Tag tJava = db.createTag("Java");
        Tag tCpp = db.createTag("C++");

        Article aPhp = db.createArticle("PHP para iniciantes", "", 1, new long[]{tPhp.id});
        Article aJava = db.createArticle("Java para iniciantes", "", 1, new long[]{tJava.id});
        Article aCpp = db.createArticle("C++ para iniciantes", "", 1, new long[]{tCpp.id});
        Article aJavaAndPhp = db.createArticle("Java e PHP para iniciantes na web", "", 1, new long[]{tJava.id, tCpp.id});

        List<Article> articles = db.getAllArticles("");
        List<Article> articlesWithPHP = db.getAllArticles(tPhp.name);

        ArticleAdapter adapter = new ArticleAdapter(articles);
        rv.setAdapter(adapter);
        db.close();
    }
}

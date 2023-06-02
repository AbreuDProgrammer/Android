package com.example.myapplicationandroid2023.db;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationandroid2023.R;

public class ArticleItem extends Activity {

    TextView title, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rvitemdb);
        Intent intent = getIntent();
        long id = Long.parseLong(intent.getStringExtra("id"));
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        Article article = db.getArticle(id);

        this.title = (TextView) findViewById(R.id.titleView);
        this.url = (TextView) findViewById(R.id.urlView);

        this.title.setText(article.title);
        this.url.setText(article.url);

    }

}

package com.example.myapplicationandroid2023;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Main2 extends Activity implements View.OnClickListener {

    Button btn;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        this.btn = (Button) findViewById(R.id.btnmain2);
        this.btn.setOnClickListener(this);

        Intent intent = getIntent();
        String str = intent.getStringExtra("nome");

        Toast.makeText(Main2.this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}

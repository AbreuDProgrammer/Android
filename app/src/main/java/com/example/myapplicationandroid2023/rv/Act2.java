package com.example.myapplicationandroid2023.rv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationandroid2023.Main2;
import com.example.myapplicationandroid2023.R;

public class Act2 extends Activity {

    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.txt = (TextView) findViewById(R.id.txtNome);
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        int idade = intent.getIntExtra("umInt", 0);
        this.txt.setText(nome);
        Toast.makeText(this, "idade: "+idade, Toast.LENGTH_SHORT).show();
    }

}

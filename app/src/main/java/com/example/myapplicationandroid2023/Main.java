package com.example.myapplicationandroid2023;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Main extends Activity implements View.OnClickListener {

    private TextView txt;
    private Button btn, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.txt = (TextView) findViewById(R.id.txt);
        this.btn = (Button) findViewById(R.id.btn);
        this.btn2 = (Button) findViewById(R.id.btn2);
        this.btn.setOnClickListener(this);
        this.btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == this.btn){
            Toast.makeText(Main.this, "EPIC", Toast.LENGTH_SHORT).show();
        }else if(view == this.btn2){
            Intent intent = new Intent(this, Main2.class);
            intent.putExtra("nome", "Valor Exemplo");
            startActivity(intent);
        }
    }
}

package com.example.myapplicationandroid2023.rv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationandroid2023.Main2;
import com.example.myapplicationandroid2023.R;

import java.util.ArrayList;
import java.util.List;

public class MainRv extends Activity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rvmain);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Aluno l = new Aluno("Leonardo", "17", R.drawable.mj);
        Aluno d = new Aluno("Danilo", "19", R.drawable.mj);
        Aluno a = new Aluno("Afonso", "18", R.drawable.mj);

        List<Aluno> lista = new ArrayList<Aluno>();
        lista.add(l);
        lista.add(d);
        lista.add(a);

        AlunoAdapter alunoAdapter = new AlunoAdapter(lista, this);
        rv.setAdapter(alunoAdapter);
    }
}

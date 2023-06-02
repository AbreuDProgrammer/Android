package com.example.myapplicationandroid2023.rv;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationandroid2023.R;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewholder>
{
    List<Aluno> alunos;
    Context context;

    public AlunoAdapter(List<Aluno> alunos, Context ctx) {
        this.alunos = alunos;
        this.context = ctx;
    }

    @Override
    public AlunoViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvitemaluno, parent, false);
        AlunoViewholder avh = new AlunoViewholder(v, this.context);
        return avh;
    }

    @Override
    public void onBindViewHolder(AlunoViewholder holder, int position) {
        holder.nomeView.setText(alunos.get(position).nome);
        holder.idadeView.setText(alunos.get(position).idade);
        holder.fotoView.setImageResource(alunos.get(position).fotoId);
    }

    @Override
    public int getItemCount() {
        return this.alunos.size();
    }

    public static class AlunoViewholder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView nomeView;
        TextView idadeView;
        ImageView fotoView;
        Context context;
        public static final String TAG = "Leoncio";

        public AlunoViewholder(View itemView, Context ctx) {
            super(itemView);
            this.context = ctx;
            itemView.setOnClickListener(this);
            this.nomeView = (TextView) itemView.findViewById(R.id.nomeView);
            this.idadeView = (TextView) itemView.findViewById(R.id.idadeView);
            this.fotoView = (ImageView) itemView.findViewById(R.id.fotoView);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "AlunoViewHolderOnClick"+getPosition()+nomeView.getText().toString());
            Intent intent = new Intent(this.context, Act2.class);
            intent.putExtra("nome", this.nomeView.getText().toString());
            this.context.startActivity(intent);
        }
    }
}

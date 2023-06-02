package com.example.myapplicationandroid2023.db;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplicationandroid2023.R;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    List<Article> articles;

    public ArticleAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public ArticleAdapter.ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvitemdb, parent, false);
        ArticleViewHolder articleViewHolder = new ArticleViewHolder(view, parent.getContext());
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.ArticleViewHolder holder, int position) {
        holder.id.setText(String.valueOf(this.articles.get(position).id));
        holder.title.setText(this.articles.get(position).title);
        holder.url.setText(this.articles.get(position).url);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id, title, url;
        Context context;

        public ArticleViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(this);
            this.id = (TextView) itemView.findViewById(R.id.idView);
            this.title = (TextView) itemView.findViewById(R.id.titleView);
            this.url = (TextView) itemView.findViewById(R.id.urlView);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(this.context, ArticleItem.class);
            intent.putExtra("id", String.valueOf(this.id.getText()));
            this.context.startActivity(intent);
            return;
        }
    }
}

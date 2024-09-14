package com.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    Context context;
    ArrayList<ModelNewsItem> articleList;

    public NewsAdapter(Context context, ArrayList<ModelNewsItem> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelNewsItem newsArticles = articleList.get(position);
        DatabaseHelper dbHelper = new DatabaseHelper(context);


        String thumbnailUri = newsArticles.getImageUrl();

        String author = newsArticles.getAuthor();
        String content = newsArticles.getContent();
        String date = newsArticles.getPublishDate();
        String url = newsArticles.getUrl();
        String title = newsArticles.getTitle();
        String desc = newsArticles.getDescription();
        String source = newsArticles.getSource();

        holder.textViewNewsTitle.setText(title);
        holder.textViewNewsDesc.setText(desc);
        holder.textViewNewsSource.setText("Source: " + source);


        if (thumbnailUri != null) {
            Picasso.get().load(thumbnailUri).into(holder.imageNewsThumbnail);

        } else {
            holder.imageNewsThumbnail.setImageResource(R.drawable.news_item);


        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsDetails.class);
                intent.putExtra("thumbnailUrl", thumbnailUri);
                intent.putExtra("title", title);
                intent.putExtra("author", author);
                intent.putExtra("content", content);
                intent.putExtra("publishDate", date);
                intent.putExtra("source", source);
                intent.putExtra("desc", desc);
                context.startActivity(intent);


            }
        });

        if (dbHelper.isNewsBookmarked(title)) {
            holder.bookmarkIcon.setImageResource(R.drawable.baseline_bookmark_added_24);
        } else {
            holder.bookmarkIcon.setImageResource(R.drawable.baseline_bookmark_add_24);
        }

        holder.bookmarkIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewsToBookmark(newsArticles, holder);
            }
        });


    }

    private void addNewsToBookmark(ModelNewsItem modelNewsItem, MyViewHolder holder) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        if (dbHelper.isNewsBookmarked(modelNewsItem.getTitle())) {
            dbHelper.deleteBookmark(modelNewsItem.getTitle());
            holder.bookmarkIcon.setImageResource(R.drawable.baseline_bookmark_add_24);
            Toast.makeText(context, "Bookmarked removed", Toast.LENGTH_SHORT).show();
        } else {
            dbHelper.addBookmark(modelNewsItem);
            holder.bookmarkIcon.setImageResource(R.drawable.baseline_bookmark_added_24);
            Toast.makeText(context, "Bookmarked", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNewsTitle, textViewNewsDesc, textViewNewsSource;
        ImageView imageNewsThumbnail, bookmarkIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNewsTitle = itemView.findViewById(R.id.newsTitle);
            textViewNewsDesc = itemView.findViewById(R.id.newsDesc);
            textViewNewsSource = itemView.findViewById(R.id.newsSource);
            imageNewsThumbnail = itemView.findViewById(R.id.newsThumbnail);
            bookmarkIcon = itemView.findViewById(R.id.imageBookmarkIcon);
        }
    }
}

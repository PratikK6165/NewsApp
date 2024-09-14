package com.newsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {
    ImageView articleThumbnail;
    AppCompatButton addBookmarkButton;
    TextView articleTitle, articleContent, articleAuthor, articlePublishedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);


        articleThumbnail = findViewById(R.id.newsThumbnailImage);
        articleTitle = findViewById(R.id.tv_newsTitle);
        articleContent = findViewById(R.id.tv_article_content);
        articleAuthor = findViewById(R.id.tv_author);
        articlePublishedDate = findViewById(R.id.tv_publicationDate);
        addBookmarkButton = findViewById(R.id.addBookmark);

        String articleThumbnailImage = getIntent().getStringExtra("thumbnailUrl");
        String title = getIntent().getStringExtra("title");
        String authorName = getIntent().getStringExtra("author");
        String content = getIntent().getStringExtra("content");
        String publicationDate = getIntent().getStringExtra("publishDate");
        String source = getIntent().getStringExtra("source");
        String description = getIntent().getStringExtra("desc");

        Picasso.get().load(articleThumbnailImage).into(articleThumbnail);

        articleTitle.setText(title);
        articleAuthor.setText("Author: " + authorName);
        articleContent.setText(content);
        articlePublishedDate.setText("Published At: " + publicationDate);
        DatabaseHelper dbHelper = new DatabaseHelper(NewsDetails.this);


        if (dbHelper.isNewsBookmarked(title)) {
            addBookmarkButton.setText("Bookmark Added");
        }

        addBookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelNewsItem modelNewsItem = new ModelNewsItem(title, source, articleThumbnailImage, description, publicationDate, content, authorName);

                if (dbHelper.isNewsBookmarked(title)) {
                    // If already bookmarked, show "bookmark added" icon and message
                    dbHelper.deleteBookmark(title);
                    addBookmarkButton.setText("Add Bookmark");
                    Toast.makeText(NewsDetails.this, "Bookmarked removed", Toast.LENGTH_SHORT).show();
                } else {
                    // If not bookmarked, add to the database and update the icon
                    dbHelper.addBookmark(modelNewsItem);
                    addBookmarkButton.setText("Remove Bookmark");
                    Toast.makeText(NewsDetails.this, "Bookmarked", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
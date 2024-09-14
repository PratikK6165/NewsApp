package com.newsapp;

public class ModelNewsItem {
    String title;
    String source;
    String imageUrl;
    String description;
    String url;
    String publishDate;
    String content;
    String author;



    public ModelNewsItem(String title, String source, String imageUrl, String description, String url, String publishDate, String content, String author) {
        this.title = title;
        this.source = source;
        this.imageUrl = imageUrl;
        this.description = description;
        this.url = url;
        this.publishDate = publishDate;
        this.content = content;
        this.author = author;
    }

    public ModelNewsItem(String title, String source, String imageUrl, String description, String publishDate, String content, String author) {
        this.title = title;
        this.source = source;
        this.imageUrl = imageUrl;
        this.description = description;
        this.publishDate = publishDate;
        this.content = content;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

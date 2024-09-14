News Android App 📱📰

This repository contains a simple news app built in Java, which fetches the latest news articles from an API and allows users to bookmark their favorite articles for later reading.
Features

  News Feed: Fetches and displays the latest news articles in a scrollable list.
  Bookmarking: Users can bookmark news articles by clicking on a bookmark icon. Bookmarked articles are saved locally and displayed in the Bookmarks section.
  Offline Access: Bookmarked articles are stored using SQLite and can be accessed without an internet connection.
  Detailed News View: Users can click on any article to view its full content.

Tech Stack

  IDE: Android Studio
  Language: Java
  Architecture: Single activity with multiple fragments
  Database: SQLite for storing bookmarked news articles

Libraries Used

  Volley: For making network requests to fetch news data from the API.
  Picasso: For loading and displaying images in the app efficiently.

API

   This app fetches data from the News API.

Screenshots:

![NewsApp1](https://github.com/user-attachments/assets/24ba55b6-6686-4a39-9e42-fdf772e1e945) ![NewsApp2](https://github.com/user-attachments/assets/422dd7c3-4e7e-410f-9f95-14861db932a3)
![NewsApp3](https://github.com/user-attachments/assets/4f279c28-3499-4c6c-9a14-38b4e79623dd) ![NewsApp4](https://github.com/user-attachments/assets/35be70df-63f2-4fe7-b07c-abdc310f6235)


How It Works

  Fetching News: The app fetches news articles from the News API using Volley. The news is displayed in a RecyclerView in the News fragment.
  Bookmarking: When a user taps the bookmark icon on an article, it gets saved to a local SQLite database. Bookmarked articles are displayed in the Bookmarks fragment.
  Detailed News View: Clicking on any article from the News or Bookmark list shows a detailed view of the article.


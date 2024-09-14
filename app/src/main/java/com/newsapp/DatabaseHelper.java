package com.newsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bookmarks.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "newsBookmark";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESC = "description";
    private static final String COLUMN_IMAGE_URL = "imageUrl";
    private static final String COLUMN_SOURCE = "source";
    private static final String COLUMN_PUBLISHED_DATE = "publishedAt";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_LINK = "url";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESC + " TEXT, " +
                COLUMN_IMAGE_URL + " TEXT, " +
                COLUMN_SOURCE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_CONTENT + " TEXT, " +
                COLUMN_PUBLISHED_DATE + " TEXT " + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addBookmark(ModelNewsItem newsItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, newsItem.getTitle());
        values.put(COLUMN_DESC, newsItem.getDescription());
        values.put(COLUMN_IMAGE_URL, newsItem.getImageUrl());
        values.put(COLUMN_SOURCE, newsItem.getSource());
        values.put(COLUMN_AUTHOR, newsItem.getAuthor());
        values.put(COLUMN_CONTENT, newsItem.getContent());
        values.put(COLUMN_PUBLISHED_DATE, newsItem.getPublishDate());


        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<ModelNewsItem> getAllBookmarks() {
        ArrayList<ModelNewsItem> bookmarks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ModelNewsItem newsItem = new ModelNewsItem(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SOURCE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_URL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESC)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PUBLISHED_DATE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTHOR))


                );
                bookmarks.add(newsItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bookmarks;
    }

    public boolean isNewsBookmarked(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_TITLE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{title});

        boolean isBookmarked = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isBookmarked;
    }
    public void deleteBookmark(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_TITLE + " = ?", new String[]{title});
        db.close();
    }
}

package com.newsapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationItemView = findViewById(R.id.bottom_navigation);
        bottomNavigationItemView.setOnNavigationItemSelectedListener(onItemSelectedListener);

        // Set default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new NewsFragment()).commit();
        }


    }

    private BottomNavigationView.OnNavigationItemSelectedListener onItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.nav_news) {
                fragment = new NewsFragment();

            } else if (itemId == R.id.nav_bookmarks) {
                fragment = new BookmarkFragment();
            }

            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }

            return true;
        }
    };
}
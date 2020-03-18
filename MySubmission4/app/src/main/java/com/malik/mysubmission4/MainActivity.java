package com.malik.mysubmission4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.malik.mysubmission4.fragment.FavoritFragment;
import com.malik.mysubmission4.fragment.MovieFragment;
import com.malik.mysubmission4.fragment.SerialFragment;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.movie);
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        if (savedInstanceState == null){
            bottomNavigationView.setSelectedItemId(R.id.navigation_movie);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(MainActivity.this).inflate(R.menu.menu_language, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_english:
                setLocale("en");
                Toast.makeText(this, getString(R.string.english), Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_indonesian:
                setLocale("in");
                Toast.makeText(this, getString(R.string.indonesian), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()){
                case R.id.navigation_movie :
                    fragment = new MovieFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment ,fragment.getClass().getSimpleName()).commit();
                    if (getSupportActionBar() != null){
                        getSupportActionBar().setTitle(R.string.movie);
                    }
                    return true;
                case R.id.navigation_tv :
                    fragment = new SerialFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName()).commit();
                    if (getSupportActionBar() != null){
                        getSupportActionBar().setTitle(R.string.serial);
                    }
                    return true;
                case R.id.navigation_favorite :
                    fragment = new FavoritFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName()).commit();
                    if (getSupportActionBar() != null){
                        getSupportActionBar().setTitle(R.string.favorit);
                    }
                    return true;
            }
            return false;
        }
    };

    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration conf = resources.getConfiguration();
        conf.locale = locale;
        resources.updateConfiguration(conf, displayMetrics);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }
}

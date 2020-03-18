package com.malik.mysubmission4;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.malik.mysubmission4.config.UrlAPI;
import com.malik.mysubmission4.db.dbmovie.AppDatabase;
import com.malik.mysubmission4.db.dbmovie.MovieFavorite;
import com.malik.mysubmission4.model.Detail;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_DETAIL = "EXTRA_DETAIL";
    private ImageView imgPosterPath;
    private TextView tvID;
    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvReleaseDate;
    private TextView tvRating;
    private String TAG = DetailActivity.class.getSimpleName();
    private Detail detail;
    private AppDatabase db;
    MovieFavorite movieFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //init DB
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "moviefavorite").allowMainThreadQueries().build();
        detail = getIntent().getParcelableExtra(EXTRA_DETAIL);
        getInit();
        if (detail != null) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(getString(R.string.detail_film) + " " + detail.getTitle());
            }

            if (detail.getPosterPath() != null) {
                Glide.with(DetailActivity.this)
                        .load(UrlAPI.URL_POSTER + detail.getPosterPath()).into(imgPosterPath);
            }
            tvID.setText(String.valueOf(detail.getId()));
            tvTitle.setText(String.valueOf(detail.getTitle()));
            if (detail.getOverview().equals("")) {
                tvDescription.setText(getString(R.string.lorem_ipsum));
            } else {
                tvDescription.setText(detail.getOverview());
            }
            tvReleaseDate.setText(detail.getReleaseDate());
            tvRating.setText(String.valueOf(detail.getVote_average()));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_favorite, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.ic_favorite){
                    movieFavorite = new MovieFavorite();
                    movieFavorite.setTitle(detail.getTitle());
                    movieFavorite.setDescription(detail.getOverview());
                    movieFavorite.setPoster(detail.getPosterPath());
                    movieFavorite.setRating(detail.getVote_average());
                    movieFavorite.setReleasedate(detail.getReleaseDate());
                    // insert in database
                    db.queryDAO().insertAll(movieFavorite);
            Toast.makeText(this, getString(R.string.add_toast_favorite), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getInit() {
        imgPosterPath = findViewById(R.id.img_poster);
        tvID = findViewById(R.id.tv_id);
        tvTitle = findViewById(R.id.tv_title);
        tvDescription = findViewById(R.id.tv_description);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvRating = findViewById(R.id.tv_rating);

    }

}

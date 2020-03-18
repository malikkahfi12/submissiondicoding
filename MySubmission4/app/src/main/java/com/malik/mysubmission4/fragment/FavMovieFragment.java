package com.malik.mysubmission4.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.malik.mysubmission4.R;
import com.malik.mysubmission4.adapter.MovieFavoriteAdapter;
import com.malik.mysubmission4.db.dbmovie.AppDatabase;
import com.malik.mysubmission4.db.dbmovie.MovieFavorite;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavMovieFragment extends Fragment {


    private List<MovieFavorite> movieFavorites = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav_movie, container, false);

        AppDatabase db = Room.databaseBuilder(container.getContext(),
                AppDatabase.class, "moviefavorite").allowMainThreadQueries().build();

        // Init List from DB

            movieFavorites = db.queryDAO().getAll();



        Log.e("onCreateView: ", String.valueOf(movieFavorites.size()));
        initRecyclerView(view, container.getContext());
        return view;
    }

    private void initRecyclerView(View view, Context context) {
        RecyclerView rcFavoriteMovie = view.findViewById(R.id.rc_fav_movie);
        rcFavoriteMovie.setLayoutManager(new LinearLayoutManager(context));
        rcFavoriteMovie.setHasFixedSize(true);
        MovieFavoriteAdapter adapter = new MovieFavoriteAdapter(movieFavorites, context);
        adapter.notifyDataSetChanged();
        rcFavoriteMovie.setAdapter(adapter);
    }





}

package com.malik.mysubmission4.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.malik.mysubmission4.R;
import com.malik.mysubmission4.adapter.MovieAdapter;
import com.malik.mysubmission4.pojo.Movie.ResultsItem;
import com.malik.mysubmission4.viewmodel.ViewModelMovie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private View view;
    private RecyclerView rcViewMovie;
    private MovieAdapter adapter;
    private ProgressBar progressBar;
    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movie, container, false);
        getInit(view);
        ViewModelMovie viewModelMovie;
        viewModelMovie = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ViewModelMovie.class);
        viewModelMovie.setMovie(container.getContext());
        showLoading(true);
        viewModelMovie.getResultMovie().observe(this, new Observer<ArrayList<ResultsItem>>() {
            @Override
            public void onChanged(ArrayList<ResultsItem> resultsItems) {
                if (resultsItems != null){
                    adapter = new MovieAdapter(container.getContext(), resultsItems);
                    rcViewMovie.setLayoutManager(new LinearLayoutManager(container.getContext()));
                    adapter.notifyDataSetChanged();
                    rcViewMovie.setAdapter(adapter);
                    rcViewMovie.setHasFixedSize(true);
                    showLoading(false);
                }
            }
        });
        return view;
    }

    private void getInit(View view){
        rcViewMovie = view.findViewById(R.id.rc_view_movie);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    private void showLoading(Boolean state){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}

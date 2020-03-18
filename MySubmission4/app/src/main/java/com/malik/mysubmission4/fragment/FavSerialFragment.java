package com.malik.mysubmission4.fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.malik.mysubmission4.R;
import com.malik.mysubmission4.adapter.SerialFavoriteAdapter;
import com.malik.mysubmission4.db.dbserial.AppSerialDatabase;
import com.malik.mysubmission4.db.dbserial.SerialFavorite;

import java.util.ArrayList;
import java.util.List;


public class FavSerialFragment extends Fragment {

    private List<SerialFavorite> serialFavorites = new ArrayList<>();
    public FavSerialFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fav_serial, container, false);
        AppSerialDatabase db = Room.databaseBuilder(container.getContext(),AppSerialDatabase.class,"serialfavorite").allowMainThreadQueries().build();
        serialFavorites = db.querySerialDAO().getAll();
        initRecyclerView(view, container.getContext());
        return view;
    }

    private void initRecyclerView(View view, Context context) {
        RecyclerView rcFavoriteMovie = view.findViewById(R.id.rc_fav_serial);
        rcFavoriteMovie.setLayoutManager(new LinearLayoutManager(context));
        rcFavoriteMovie.setHasFixedSize(true);
        SerialFavoriteAdapter adapter = new SerialFavoriteAdapter(serialFavorites, context);
        adapter.notifyDataSetChanged();
        rcFavoriteMovie.setAdapter(adapter);
    }

}

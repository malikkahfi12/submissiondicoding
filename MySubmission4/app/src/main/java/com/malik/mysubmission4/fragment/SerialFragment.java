package com.malik.mysubmission4.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.malik.mysubmission4.R;
import com.malik.mysubmission4.adapter.SerialAdapter;
import com.malik.mysubmission4.pojo.Serial.ResultsItem;
import com.malik.mysubmission4.viewmodel.ViewModelSerial;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SerialFragment extends Fragment {

    private View view;
    private RecyclerView rcViewSerial;
    private ProgressBar pgBar;
    private SerialAdapter adapter;
    public SerialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_serial, container, false);
        getInit(view);
        ViewModelSerial viewModelSerial;
        viewModelSerial = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ViewModelSerial.class);
        viewModelSerial.setSerial(container.getContext());
        showLoading(true);
        viewModelSerial.getResultSerial().observe(this, new Observer<ArrayList<ResultsItem>>() {
            @Override
            public void onChanged(ArrayList<ResultsItem> resultsItems) {
                if (resultsItems != null){
                    adapter = new SerialAdapter(container.getContext(), resultsItems);
                    rcViewSerial.setLayoutManager(new LinearLayoutManager(container.getContext()));
                    adapter.notifyDataSetChanged();
                    rcViewSerial.setAdapter(adapter);
                    rcViewSerial.setHasFixedSize(true);
                    showLoading(false);
                }
            }
        });
        return view;
    }

    private void getInit(View view){
        rcViewSerial = view.findViewById(R.id.rc_view_serial);
        pgBar = view.findViewById(R.id.progress_bar);
    }

    private void showLoading(boolean state){
        if (state){
            pgBar.setVisibility(View.VISIBLE);
        } else {
            pgBar.setVisibility(View.GONE);
        }
    }

}

package com.malik.mysubmission4.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.malik.mysubmission4.R;
import com.malik.mysubmission4.adapter.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritFragment extends Fragment {
    private View view;
    public FavoritFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.fragment_favorit, container, false);
       final TabLayout tabLayout = view.findViewById(R.id.tab_layout);
       final ViewPager viewPager = view.findViewById(R.id.view_pager);

       if (getActivity() != null){
           ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
           adapter.AddFragment(new FavMovieFragment(), "Favorit Movie");
           adapter.AddFragment(new FavSerialFragment(), "Favorit Serial");
           viewPager.setAdapter(adapter);
           viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
           tabLayout.setupWithViewPager(viewPager);

       }



        return view;
    }

}

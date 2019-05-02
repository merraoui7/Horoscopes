package com.zeneo.horoscope.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeneo.horoscope.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoveFragment extends Fragment {


    public LoveFragment() {
        // Required empty public constructor
    }

    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_love, container, false);


        adapter = new com.zeneo.horoscope.Adapters.PagerAdapter(getChildFragmentManager());
        ((com.zeneo.horoscope.Adapters.PagerAdapter) adapter).addFrag("هذا الأسبوع",new WeeklyLoveFragment());
        ((com.zeneo.horoscope.Adapters.PagerAdapter) adapter).addFrag("اليوم",new DailyLoveFragment());

        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(3);

        ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(1).setScaleY(1.4f);
        ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(1).setScaleX(1.4f);


        viewPager.setOffscreenPageLimit(5);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).setScaleX(1.4f);
                ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).setScaleY(1.4f);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).setScaleX(1);
                ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).setScaleY(1);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}

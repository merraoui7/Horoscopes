package com.zeneo.horoscope.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.zeneo.horoscope.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HoroscopesFragment extends Fragment {


    public HoroscopesFragment() {
        // Required empty public constructor
    }

    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_horoscopes, container, false);

        adapter = new com.zeneo.horoscope.Adapters.PagerAdapter(getChildFragmentManager());
        ((com.zeneo.horoscope.Adapters.PagerAdapter) adapter).addFrag("هذه السنة",new YearFragment());
        ((com.zeneo.horoscope.Adapters.PagerAdapter) adapter).addFrag("هذا الشهر",new MonthFragment());
        ((com.zeneo.horoscope.Adapters.PagerAdapter) adapter).addFrag("هذا الاسبوع",new WeekFragment());
        ((com.zeneo.horoscope.Adapters.PagerAdapter) adapter).addFrag("اليوم",new TodayFragment());
        ((com.zeneo.horoscope.Adapters.PagerAdapter) adapter).addFrag("مميزات الشخصية",new CharFragment());


        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(3);

        ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(3).setScaleY(1.4f);
        ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(3).setScaleX(1.4f);


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

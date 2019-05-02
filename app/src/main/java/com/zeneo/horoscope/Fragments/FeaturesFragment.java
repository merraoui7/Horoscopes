package com.zeneo.horoscope.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zeneo.horoscope.LoveActivity;
import com.zeneo.horoscope.LuckyActivity;
import com.zeneo.horoscope.MainActivity;
import com.zeneo.horoscope.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeaturesFragment extends Fragment {


    public FeaturesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_features, container, false);

        LinearLayout lt1 = view.findViewById(R.id.love_lt);
        lt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoveActivity.class);
                getContext().startActivity(intent);
            }
        });

        LinearLayout lucky_lt = view.findViewById(R.id.lucky_lt);
        lucky_lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LuckyActivity.class);
                getContext().startActivity(intent);
            }
        });


        LinearLayout daily_lt = view.findViewById(R.id.daily_lt);
        daily_lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getContext()).goToDailyHoro();
            }
        });

        return view;
    }

}

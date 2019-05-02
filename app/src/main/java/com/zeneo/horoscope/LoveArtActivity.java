package com.zeneo.horoscope;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.zeneo.horoscope.Adapters.RecAdapter;
import com.zeneo.horoscope.Data.CompatibilityData;
import com.zeneo.horoscope.Model.Compatibility;

import java.util.ArrayList;
import java.util.List;

import me.itangqi.waveloadingview.WaveLoadingView;

public class LoveArtActivity extends AppCompatActivity {


    Compatibility compat;
    TextView title;
    TextView text;
    WaveLoadingView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_art);

        title = findViewById(R.id.title);
        text = findViewById(R.id.text);
        loadingView = findViewById(R.id.waveLoadingView);

        int index1 = getIntent().getExtras().getInt("index1");
        int index2 = getIntent().getExtras().getInt("index2");

        compat = new CompatibilityData().initializeData(index1,index2);

        text.setText(compat.getText());
        title.setText("الرجل "+zodiak[index1]+" والمرأة "+zodiak[index2]);
        loadingView.setProgressValue(Integer.parseInt(compat.getPercent()));

    }

    String zodiak [] = {
            "الحمل",
            "الثور",
            "الجوزاء",
            "السرطان",
            "الأسد",
            "العذراء",
            "الميزان",
            "العقرب",
            "القوس",
            "الجدي",
            "الدلو",
            "الحوت",
    };

}

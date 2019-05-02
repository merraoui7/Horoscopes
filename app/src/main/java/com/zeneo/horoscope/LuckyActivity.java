package com.zeneo.horoscope;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class LuckyActivity extends AppCompatActivity {


    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;

    TextView number1;
    TextView number2;
    TextView number3;
    TextView number4;

    TextView color_textView;
    TextView numbers_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("حظك اليومي");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.getNavigationIcon().setColorFilter(Color.parseColor("#3298FE"), PorterDuff.Mode.SRC_IN);

        layout1 = findViewById(R.id.lucky_lt1);
        layout2 = findViewById(R.id.lucky_lt2);
        layout3 = findViewById(R.id.lucky_lt3);
        layout4 = findViewById(R.id.lucky_lt4);

        number1 =(TextView) layout1.getChildAt(0);
        number2 =(TextView) layout2.getChildAt(0);
        number3 =(TextView) layout3.getChildAt(0);
        number4 =(TextView) layout4.getChildAt(0);

        numbers_textView = findViewById(R.id.numbers_text);
        color_textView = findViewById(R.id.color_text);

        Random rnd = new Random();

        int rndColor = rnd.nextInt(colorsDrawa.length-1);

        int rndNum1 = rnd.nextInt(100);
        int rndNum2 = rnd.nextInt(100);
        int rndNum3 = rnd.nextInt(100);
        int rndNum4 = rnd.nextInt(100);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int index = preferences.getInt("index",0);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        SharedPreferences.Editor spEditor = getSharedPreferences("Lucky", Context.MODE_PRIVATE).edit();
        SharedPreferences sp = getSharedPreferences("Lucky",Context.MODE_PRIVATE);

        if (sp.getInt(formattedDate+"-num1-"+index,101)==101){

            layout1.setBackground(getResources().getDrawable(colorsDrawa[rndColor]));
            layout2.setBackground(getResources().getDrawable(colorsDrawa[rndColor]));
            layout3.setBackground(getResources().getDrawable(colorsDrawa[rndColor]));
            layout4.setBackground(getResources().getDrawable(colorsDrawa[rndColor]));

            number1.setText(rndNum1+"");
            number2.setText(rndNum2+"");
            number3.setText(rndNum3+"");
            number4.setText(rndNum4+"");


            numbers_textView.setText("أرقام حظك اليوم هي : "+rndNum1+"-"+rndNum2+"-"+rndNum3+"-"+rndNum4);
            color_textView.setText("لون حظك اليوم هو: "+colorsNames[rndColor]);

            spEditor.putInt(formattedDate+"-num1-"+index,rndNum1);
            spEditor.putInt(formattedDate+"-num2-"+index,rndNum2);
            spEditor.putInt(formattedDate+"-num3-"+index,rndNum3);
            spEditor.putInt(formattedDate+"-num4-"+index,rndNum4);
            spEditor.putInt(formattedDate+"-color-"+index,rndColor);

            spEditor.apply();

        }else {

            rndNum1 = sp.getInt(formattedDate+"-num1-"+index,rndNum1);
            rndNum2 = sp.getInt(formattedDate+"-num2-"+index,rndNum2);
            rndNum3 = sp.getInt(formattedDate+"-num3-"+index,rndNum3);
            rndNum4 = sp.getInt(formattedDate+"-num4-"+index,rndNum4);
            rndColor = sp.getInt(formattedDate+"-color-"+index,rndColor);

            layout1.setBackground(getResources().getDrawable(colorsDrawa[rndColor]));
            layout2.setBackground(getResources().getDrawable(colorsDrawa[rndColor]));
            layout3.setBackground(getResources().getDrawable(colorsDrawa[rndColor]));
            layout4.setBackground(getResources().getDrawable(colorsDrawa[rndColor]));

            number1.setText(rndNum1+"");
            number2.setText(rndNum2+"");
            number3.setText(rndNum3+"");
            number4.setText(rndNum4+"");

            color_textView.setText("لون حظك اليوم هو: "+colorsNames[rndColor]);


            numbers_textView.setText("أرقام حظك اليوم هي : "+rndNum1+"-"+rndNum2+"-"+rndNum3+"-"+rndNum4);
            color_textView.setText("لون حظك اليوم هو: "+colorsNames[rndColor]);

        }


    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    int colorsDrawa [] = {
            R.drawable.circle_blue,
            R.drawable.circle_cyan,
            R.drawable.circle_green,
            R.drawable.circle_grey,
            R.drawable.circle_magenta,
            R.drawable.circle_orange,
            R.drawable.circle_purple,
            R.drawable.circle_red,
            R.drawable.circle_yellow
    };

    String colorsNames [] = {
            "الأزرق",
            "الأزرق السماوي",
            "الأخضر",
            "الرمادي",
            "الأرجواني",
            "البرتقالي",
            "البنفسجي",
            "الأحمر",
            "الأصفر",
    };




}

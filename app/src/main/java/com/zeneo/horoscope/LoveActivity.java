package com.zeneo.horoscope;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;


public class LoveActivity extends AppCompatActivity {



    LinearLayout lt1,lt2,lt3,lt4,lt5,lt6,lt7,lt8,lt9,lt10,lt11,lt12;
    LinearLayout male_lt,female_lt;

    ImageView malelogo , femalelogo;

    RelativeLayout check_lt;

    int index1;
    int index2;

    boolean isTheFirstOption = true;
    boolean isTheSecendOptionReady = true;
    boolean isTheFinalOption = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);


        lt1 = findViewById(R.id.lt1);
        lt2 = findViewById(R.id.lt2);
        lt3 = findViewById(R.id.lt3);
        lt4 = findViewById(R.id.lt4);
        lt5 = findViewById(R.id.lt5);
        lt6 = findViewById(R.id.lt6);
        lt7 = findViewById(R.id.lt7);
        lt8 = findViewById(R.id.lt8);
        lt9 = findViewById(R.id.lt9);
        lt10 = findViewById(R.id.lt10);
        lt11 = findViewById(R.id.lt11);
        lt12 = findViewById(R.id.lt12);
        malelogo = findViewById(R.id.male);
        femalelogo = findViewById(R.id.female);
        male_lt = findViewById(R.id.male_lt);
        female_lt = findViewById(R.id.female_lt);
        check_lt = findViewById(R.id.check_lt);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("توافق الأبراج");

        PulsatorLayout pulsator = findViewById(R.id.pulsator);
        pulsator.start();
        PulsatorLayout pulsator2 = findViewById(R.id.pulsator1);
        pulsator2.start();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.getNavigationIcon().setColorFilter(Color.parseColor("#3298FE"), PorterDuff.Mode.SRC_IN);

        layoutClick(lt1,0);
        layoutClick(lt2,1);
        layoutClick(lt3,2);
        layoutClick(lt4,3);
        layoutClick(lt5,4);
        layoutClick(lt6,5);
        layoutClick(lt7,6);
        layoutClick(lt8,7);
        layoutClick(lt9,8);
        layoutClick(lt10,9);
        layoutClick(lt11,10);
        layoutClick(lt12,11);


        male_lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male_lt.setBackground(getResources().getDrawable(android.R.color.transparent));
                malelogo.setImageResource(R.drawable.ic_male);
                isTheFirstOption = true;
                isTheFinalOption = false;

                check_lt.getChildAt(0).setSelected(false);

            }
        });
        female_lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female_lt.setBackground(getResources().getDrawable(android.R.color.transparent));
                femalelogo.setImageResource(R.drawable.ic_female);
                isTheFinalOption = false;
                isTheSecendOptionReady = true;

                check_lt.getChildAt(0).setSelected(false);

            }
        });

        check_lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTheFinalOption){
                    HeartDialog dialog = new HeartDialog(LoveActivity.this,index1,index2);
                    dialog.show();
                }
            }
        });



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    int draw[] = {
            R.drawable.logo1,
            R.drawable.logo2,
            R.drawable.logo3,
            R.drawable.logo4,
            R.drawable.logo5,
            R.drawable.logo6,
            R.drawable.logo7,
            R.drawable.logo8,
            R.drawable.logo9,
            R.drawable.logo10,
            R.drawable.logo11,
            R.drawable.logo12
    };

    public void layoutClick(LinearLayout lt , final int index ){
        lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isTheFinalOption){
                    if(isTheFirstOption){
                        malelogo.setImageResource(draw[index]);
                        index1 = index;
                        isTheFirstOption = false;
                        if (!isTheSecendOptionReady){
                            check_lt.getChildAt(0).setSelected(true);
                        }
                        male_lt.setBackground(getResources().getDrawable(R.drawable.zodiak_selected_bg));
                    }else if (isTheSecendOptionReady){
                        femalelogo.setImageResource(draw[index]);
                        isTheFinalOption = true;
                        isTheSecendOptionReady = false;
                        index2 = index;
                        female_lt.setBackground(getResources().getDrawable(R.drawable.zodiak_selected_bg));
                        check_lt.getChildAt(0).setSelected(true);

                    }
                }


            }
        });
    }

}

package com.zeneo.horoscope;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;
import com.zeneo.horoscope.Adapters.RecAdapter;
import com.zeneo.horoscope.Model.Zodiak;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PickerActivity extends AppCompatActivity {


    List<Zodiak> list = new ArrayList<>();

    List<ImageView> listimg = new ArrayList<>();
    DiscreteScrollView scrollView;
    TextView birthPicket;


    ImageView left , right;
    int page = 0;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);


        RelativeLayout check_lt = findViewById(R.id.check_lt);
        check_lt.getChildAt(0).setSelected(true);


        float dip = 50f;
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );

        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        birthPicket = findViewById(R.id.datePicker);


        list.add(new Zodiak("الحمل","19/4-21/3",R.drawable.logo1));
        list.add(new Zodiak("الثور","19/5-20/4",R.drawable.logo2));
        list.add(new Zodiak("الجوزاء","20/6-21/5",R.drawable.logo3));
        list.add(new Zodiak("السرطان","22/7-21/6",R.drawable.logo4));
        list.add(new Zodiak("الأسد","22/8-23/7",R.drawable.logo5));
        list.add(new Zodiak("العذراء","22/9-23/8",R.drawable.logo6));
        list.add(new Zodiak("المبزان","22/10-23/9",R.drawable.logo7));
        list.add(new Zodiak("العقرب","21/11-23/10",R.drawable.logo8));
        list.add(new Zodiak("القوس","21/12-22/11",R.drawable.logo9));
        list.add(new Zodiak("الجدي","19/1-22/12",R.drawable.logo10));
        list.add(new Zodiak("الدلو","18/2-20/1",R.drawable.logo11));
        list.add(new Zodiak("الحوت","20/3-19/2",R.drawable.logo12));


        listimg.add((ImageView)findViewById(R.id.image1));
        listimg.add((ImageView)findViewById(R.id.image2));
        listimg.add((ImageView)findViewById(R.id.image3));
        listimg.add((ImageView)findViewById(R.id.image4));
        listimg.add((ImageView)findViewById(R.id.image5));
        listimg.add((ImageView)findViewById(R.id.image6));
        listimg.add((ImageView)findViewById(R.id.image7));
        listimg.add((ImageView)findViewById(R.id.image8));
        listimg.add((ImageView)findViewById(R.id.image9));
        listimg.add((ImageView)findViewById(R.id.image10));
        listimg.add((ImageView)findViewById(R.id.image11));
        listimg.add((ImageView)findViewById(R.id.image12));




        InfiniteScrollAdapter adapter = InfiniteScrollAdapter.wrap(new RecAdapter(PickerActivity.this,list));
        scrollView = findViewById(R.id.picker);
        scrollView.setAdapter(adapter);


        scrollView.setSlideOnFling(true);
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.6f)
                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                .setPivotY(Pivot.Y.CENTER) // CENTER is a default one
                .build());
        scrollView.setOffscreenItems(3);



        scrollView.addScrollStateChangeListener(new DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder>() {
            @Override
            public void onScrollStart(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {
                Log.i("scrollp","scrollend");
            }

            @Override
            public void onScrollEnd(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {

                for (int i = 0 ; i < listimg.size() ; i++){
                    if(i==(adapterPosition+9)%12){
                        listimg.get(i).setAlpha(1f);
                    }else listimg.get(i).setAlpha(0f);
                }
                page = (adapterPosition+9)%12;

            }

            @Override
            public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable RecyclerView.ViewHolder currentHolder, @Nullable RecyclerView.ViewHolder newCurrent) {
                Log.i("scrollp",scrollPosition+"");
                Log.i("currentp",currentPosition+"");
                Log.i("newp",newPosition+"");

                if(scrollPosition<0){
                    listimg.get((currentPosition+9)%12).setAlpha(1+scrollPosition);
                    listimg.get((newPosition+9)%12).setAlpha(-scrollPosition);
                } else {
                    listimg.get((currentPosition+9)%12).setAlpha(1-scrollPosition);
                    listimg.get((newPosition+9)%12).setAlpha(scrollPosition);
                }

            }
        });

        scrollView.setOffscreenItems(3);


        ((View)left.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollToPosition(scrollView.getCurrentItem()-1);

            }
        });
        ((View)right.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollToPosition(scrollView.getCurrentItem()+1);
            }
        });


        birthPicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PickerActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                Toast.makeText(getApplicationContext(),month+1+"",Toast.LENGTH_SHORT).show();
                int defaullPage = (scrollView.getCurrentItem()-((scrollView.getCurrentItem()+9)%12));
                switch (month+1){
                    case 1:
                        if(day>=20){
                            scrollView.smoothScrollToPosition(defaullPage+10);
                        }else {
                            scrollView.smoothScrollToPosition(defaullPage+9);
                        }
                        break;
                    case 2:
                        if(day>=19){
                            scrollView.smoothScrollToPosition(defaullPage+11);
                        }else {
                            scrollView.smoothScrollToPosition(defaullPage+10);
                        }
                        break;
                    case 3:
                        if(day>=21){
                            scrollView.smoothScrollToPosition(defaullPage+0);
                        }else {
                            scrollView.smoothScrollToPosition(defaullPage+11);
                        }
                        break;
                    case 4:
                        if(day>=20){
                            scrollView.smoothScrollToPosition(defaullPage+1);
                        }else {
                            scrollView.smoothScrollToPosition(defaullPage+0);
                        }
                        break;
                    case 5:
                        if(day>=21){
                            scrollView.smoothScrollToPosition(defaullPage+2);
                        }else {
                            scrollView.smoothScrollToPosition(defaullPage+1);
                        }
                        break;
                    case 6:
                        if(day>=21){
                            scrollView.smoothScrollToPosition(defaullPage+3);
                        }else {
                            scrollView.smoothScrollToPosition(defaullPage+2);
                        }
                        break;
                    case 7:
                        if(day>=23){
                            scrollView.smoothScrollToPosition(defaullPage+4);
                        }else {
                            scrollView.smoothScrollToPosition(defaullPage+3);
                        }
                        break;
                    case 8:
                        if(day>=23){
                            scrollView.smoothScrollToPosition(defaullPage+5);
                        }else {
                            scrollView.smoothScrollToPosition(defaullPage+4);
                        }
                        break;
                    case 9:
                        if(day>=23){
                            scrollView.smoothScrollToPosition(defaullPage+6);
                        }else {
                            scrollView.smoothScrollToPosition(defaullPage+5);
                        }
                        break;
                    case 10:
                        if(day>=23){
                            scrollView.smoothScrollToPosition(defaullPage+7);
                        }else {
                            scrollView.smoothScrollToPosition(defaullPage+6);
                        }
                        break;
                    case 11:
                        if(day>=22){
                            scrollView.smoothScrollToPosition(defaullPage+8);
                        }else {
                            scrollView.smoothScrollToPosition(defaullPage+7);
                        }
                        break;
                    case 12:
                        if(day>=22){
                            scrollView.smoothScrollToPosition(defaullPage+9);
                        }else {
                            scrollView.smoothScrollToPosition(defaullPage+8);
                        }
                        break;
                }

            }
        };


        check_lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("index", (scrollView.getCurrentItem()+9)%12);
                editor.putBoolean("isPicked",true);
                editor.apply();

                try {
                    if(getIntent().getStringExtra("from").equals("main")){
                        Intent intent = new Intent("finish_activity");
                        sendBroadcast(intent);
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("zodiakNum",(scrollView.getCurrentItem()+9)%12);
                startActivity(intent);
                finish();
            }
        });


    }

    public static final String PREFS_NAME = "zodiak";


}

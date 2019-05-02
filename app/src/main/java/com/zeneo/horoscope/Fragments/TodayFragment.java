package com.zeneo.horoscope.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeneo.horoscope.LuckyActivity;
import com.zeneo.horoscope.MainActivity;
import com.zeneo.horoscope.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayFragment extends Fragment {


    public TodayFragment() {
        // Required empty public constructor
    }

    LinearLayout layout1 , layout2 ;

    TextView textView;
    int zodiakNum = 0;
    TextView title;

    int num1,num2,num3;
    Random random = new Random();

    LinearLayout lucky_lt;

    TextView textView1,textView2,textView3;
    ImageView imageView1,imageView2,imageView3;

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
            "الحوت"
    };

    int logos[] = {
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
            R.drawable.logo12,
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        textView = view.findViewById(R.id.textView);
        layout1 = view.findViewById(R.id.pLayout);
        layout2 = view.findViewById(R.id.cLayout);
        zodiakNum = ((MainActivity)getContext()).getIntent().getExtras().getInt("zodiakNum");
        title = view.findViewById(R.id.title);
        title.setText("برج "+zodiak[zodiakNum]+" اليوم ");

        lucky_lt = view.findViewById(R.id.lucky_lt);
        lucky_lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LuckyActivity.class);
                getContext().startActivity(intent);
            }
        });

        num1 = random.nextInt(11);
        num2 = random.nextInt(11);
        num3 = random.nextInt(11);

        textView1 = view.findViewById(R.id.careerZod);
        textView2 = view.findViewById(R.id.friendZod);
        textView3 = view.findViewById(R.id.loveZod);

        imageView1 = view.findViewById(R.id.careerZodLogo);
        imageView2 = view.findViewById(R.id.friendZodLogo);
        imageView3 = view.findViewById(R.id.loveZodLogo);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        SharedPreferences.Editor spEditor = getContext().getSharedPreferences("Comptability", Context.MODE_PRIVATE).edit();
        SharedPreferences sp = getContext().getSharedPreferences("Comptability",Context.MODE_PRIVATE);

        if ( sp.getInt(formattedDate+"-career-"+zodiakNum,12)==12 && sp.getInt(formattedDate+"-friend-"+zodiakNum,12)==12 && sp.getInt(formattedDate+"-love-"+zodiakNum,12)==12) {

            spEditor.putInt(formattedDate+"-career-"+zodiakNum,num1);
            spEditor.putInt(formattedDate+"-friend-"+zodiakNum,num2);
            spEditor.putInt(formattedDate+"-love-"+zodiakNum,num3);
            spEditor.apply();
            textView1.setText(zodiak[num1]);
            textView2.setText(zodiak[num2]);
            textView3.setText(zodiak[num3]);

            imageView1.setImageResource(logos[num1]);
            imageView2.setImageResource(logos[num2]);
            imageView3.setImageResource(logos[num3]);
        } else {

            textView1.setText(zodiak[sp.getInt(formattedDate+"-career-"+zodiakNum,num1)]);
            textView2.setText(zodiak[sp.getInt(formattedDate+"-friend-"+zodiakNum,num2)]);
            textView3.setText(zodiak[sp.getInt(formattedDate+"-love-"+zodiakNum,num3)]);

            imageView1.setImageResource(logos[sp.getInt(formattedDate+"-career-"+zodiakNum,num1)]);
            imageView2.setImageResource(logos[sp.getInt(formattedDate+"-friend-"+zodiakNum,num2)]);
            imageView3.setImageResource(logos[sp.getInt(formattedDate+"-love-"+zodiakNum,num3)]);

        }


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        new GetData().execute();
    }

    private String zodiac[] = {
            "Aries",
            "Taurus",
            "Gemini",
            "Cancer",
            "Leo",
            "Virgo",
            "Libra",
            "Scorpio",
            "Sagittarius",
            "Capricorn",
            "Aquarius",
            "Pisces"
    };

    public class GetData extends AsyncTask<Void,Void,Void> {

        String url = "http://www.al-abraj.com/Abraj/Daily/General/" + zodiac[zodiakNum] + "/Today";
        String content;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
        }


        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Document document = Jsoup.connect(url).get();
                content = document.getElementsByClass("HoroDailyText").last().text();
                Log.e("element_td", content);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                textView.setText(content);

                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        }
    }


}

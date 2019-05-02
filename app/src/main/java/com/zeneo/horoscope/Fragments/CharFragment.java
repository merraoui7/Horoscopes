package com.zeneo.horoscope.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeneo.horoscope.MainActivity;
import com.zeneo.horoscope.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharFragment extends Fragment {


    public CharFragment() {
        // Required empty public constructor
    }

    LinearLayout textContainer;
    int zodiakNum = 0;
    TextView textView;
    LinearLayout layout1;
    LinearLayout layout2;

    TextView zodiakName;
    TextView zodiakDate;
    ImageView zodiakImg;

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

    String zodiakbirth[] = {
            "19/4-21/3",
            "19/5-20/4",
            "20/6-21/5",
            "22/7-21/6",
            "22/8-23/7",
            "22/9-23/8",
            "22/10-23/9",
            "21/11-23/10",
            "21/12-22/11",
            "19/1-22/12",
            "18/2-20/1",
            "20/3-19/2"
    };


    int Zodiakimg [] = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img11,
            R.drawable.img12,
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_char, container, false);

        int index = ((MainActivity)getContext()).getIntent().getExtras().getInt("zodiakNum");

        textContainer = view.findViewById(R.id.textcontainer);
        zodiakNum = ((MainActivity)getContext()).getIntent().getExtras().getInt("zodiakNum")*9+8;
        textView = view.findViewById(R.id.title);
        layout1 = view.findViewById(R.id.pLayout);
        layout2 = view.findViewById(R.id.container);
        zodiakName = view.findViewById(R.id.zodiakname);
        zodiakDate = view.findViewById(R.id.zodiakdate);
        zodiakImg = view.findViewById(R.id.zodiakimg);
        textView.setText("مميزات برج "+zodiak[index]);
        new GetData().execute();

        zodiakName.setText("برج "+zodiak[index]);
        zodiakDate.setText(zodiakbirth[index]);
        zodiakImg.setImageResource(Zodiakimg[index]);

        return view;
    }


    public class GetData extends AsyncTask<Void,Void,Void> {



        String url = "http://www.elabraj.net/ar/horoscope/content/5?content_id="+zodiakNum;
        Elements content;



        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Document document = Jsoup.connect(url).get();
                content = document.getElementsByClass("horoscope-content-body").get(0).children();

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
                for (int i = 0 ; i < content.size() ; i++ ) {
                    if(content.get(i).getElementsByTag("h3").size()>0){
                        TextView textView = new TextView(getContext());
                        textView.setText(content.get(i).getElementsByTag("h3").text());
                        textView.setTextSize(20f);
                        textView.setPadding(0,10,0,10);
                        textView.setTextColor(getResources().getColor(android.R.color.white));
                        textContainer.addView(textView);
                    }else if (content.get(i).getElementsByTag("p").size()>0){
                        TextView textView = new TextView(getContext());
                        textView.setText(content.get(i).getElementsByTag("p").text());
                        textView.setTextColor(getResources().getColor(android.R.color.white));
                        textView.setTextSize(15f);
                        textView.setPadding(0,5,0,5);
                        textContainer.addView(textView);
                    }
                }
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
        }
    }


}

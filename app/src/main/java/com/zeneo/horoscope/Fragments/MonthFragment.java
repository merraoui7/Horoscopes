package com.zeneo.horoscope.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class MonthFragment extends Fragment {


    public MonthFragment() {
        // Required empty public constructor
    }

    TextView textView;
    LinearLayout layout1 , layout2 ;
    int zodiakNum = 0;
    TextView title;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_month, container, false);

        textView = view.findViewById(R.id.textView);
        layout1 = view.findViewById(R.id.pLayout);
        layout2 = view.findViewById(R.id.container);
        zodiakNum = ((MainActivity)getContext()).getIntent().getExtras().getInt("zodiakNum");
        title = view.findViewById(R.id.title);
        title.setText("برج "+zodiak[zodiakNum]+" هذا الشهر ");
        new GetData().execute();


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        String url = "http://www.al-abraj.com/Abraj/Monthly/General/" + zodiac[zodiakNum];
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

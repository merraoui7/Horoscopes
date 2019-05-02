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
public class YearFragment extends Fragment {


    public YearFragment() {
        // Required empty public constructor
    }

    View view;
    LinearLayout textcontainer;
    int zodiakNum = 0;
    TextView textView;
    LinearLayout layout1;

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
        view = inflater.inflate(R.layout.fragment_year, container, false);
        zodiakNum = ((MainActivity)getContext()).getIntent().getExtras().getInt("zodiakNum")+1;
        textcontainer = view.findViewById(R.id.container);
        layout1 = view.findViewById(R.id.pLayout);
        textView = view.findViewById(R.id.title);
        textView.setText("برج "+zodiak[zodiakNum-1]+" هذه السنة");

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        new GetData().execute();
    }


    public class GetData extends AsyncTask<Void,Void,Void> {


        String url = "http://www.elabraj.net/ar/horoscope/yearly/"+zodiakNum;
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
            LinearLayout container = view.findViewById(R.id.container);

            try {
                for (int i = 0 ; i < content.size() ; i++ ) {
                    if(content.get(i).getElementsByTag("h3").size()>0){
                        TextView textView = new TextView(getContext());
                        textView.setText(content.get(i).getElementsByTag("h3").text());
                        textView.setTextSize(20f);
                        textView.setPadding(0,10,0,10);
                        textView.setTextColor(getResources().getColor(android.R.color.white));
                        container.addView(textView);
                    }else if (content.get(i).getElementsByTag("p").size()>0){
                        TextView textView = new TextView(getContext());
                        textView.setText(content.get(i).getElementsByTag("p").text());
                        textView.setTextColor(getResources().getColor(android.R.color.white));
                        textView.setTextSize(16f);
                        container.addView(textView);
                    }
                }
                layout1.setVisibility(View.GONE);
                textcontainer.setVisibility(View.VISIBLE);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            layout1.setVisibility(View.VISIBLE);
            textcontainer.setVisibility(View.GONE);
        }
    }




}

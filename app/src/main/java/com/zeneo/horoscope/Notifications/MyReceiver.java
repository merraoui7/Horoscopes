package com.zeneo.horoscope.Notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MyReceiver extends BroadcastReceiver {

    String content;
    String url;
    int zodiakNum = 0;

    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        zodiakNum = preferences.getInt("index",0);
        try{
            new GetData().execute(context);
        }catch(Exception e){
            e.printStackTrace();
        }
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

    public class GetData extends AsyncTask<Context,Void,Void>{



        Context context;
        @Override
        protected Void doInBackground(Context... contexts) {

            this.context = contexts[0];
            try {

                url = "http://www.al-abraj.com/Abraj/Daily/General/" + zodiac[zodiakNum] + "/Today";
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
        protected void onPreExecute() {
            super.onPreExecute();
            Utils.generateNotification(context,content);

        }
    }

}

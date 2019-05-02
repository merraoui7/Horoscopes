package com.zeneo.horoscope.Model;

public class Zodiak {

    private String name;
    private String date;
    private int imageRes;

    public Zodiak(String name, String date, int imageRes) {
        this.name = name;
        this.date = date;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getImageRes() {
        return imageRes;
    }
}

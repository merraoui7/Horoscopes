package com.zeneo.horoscope.Model;

public class Compatibility {

    private String text;
    private String percent;

    public Compatibility(String text, String percent) {
        this.text = text;
        this.percent = percent;
    }

    public String getText() {
        return text;
    }

    public String getPercent() {
        return percent;
    }
}

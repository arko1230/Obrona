package com.arko.edc;

public class DataSetFirePress {
    String pressResult,pulsResult, aboutPressure, date;

    public String getPressResult() {
        return pressResult;
    }

    public void setPressResult(String pressResult) {
        this.pressResult = pressResult;
    }

    public String getPulsResult() {
        return pulsResult;
    }

    public void setPulsResult(String pulsResult) {
        this.pulsResult = pulsResult;
    }

    public String getAboutPressure() {
        return aboutPressure;
    }

    public void setAboutPressure(String aboutPressure) {
        this.aboutPressure = aboutPressure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    DataSetFirePress(){}

    public DataSetFirePress(String pressResult, String pulsResult, String aboutPressure, String date) {
        this.pressResult = pressResult;
        this.pulsResult = pulsResult;
        this.aboutPressure = aboutPressure;
        this.date = date;
    }
}

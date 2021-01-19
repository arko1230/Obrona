package com.arko.edc;

public class DataSetFireTemp {

    String aboutTemperature;
    String date;
    String tempResult;



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTempResult() {
        return tempResult;
    }

    public void setTempResult(String tempResult) {
        this.tempResult = tempResult;
    }

    public String getAboutTemperature() {
        return aboutTemperature;
    }

    public void setAboutTemperature(String aboutTemperature) {
        this.aboutTemperature = aboutTemperature;
    }

    public  DataSetFireTemp(){}

    public DataSetFireTemp(String date, String tempResult, String aboutTemperature) {
        this.date = date;
        this.tempResult = tempResult;
        this.aboutTemperature = aboutTemperature;
    }
}

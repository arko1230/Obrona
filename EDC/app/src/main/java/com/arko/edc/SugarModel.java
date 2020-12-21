package com.arko.edc;

public class SugarModel {

    String date, sugarResult, aboutSugar;

    SugarModel(){


    }


    public SugarModel(String date, String sugarResult, String aboutSugar) {
        this.date = date;
        this.sugarResult = sugarResult;
        this.aboutSugar = aboutSugar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSugarResult() {
        return sugarResult;
    }

    public void setSugarResult(String sugarResult) {
        this.sugarResult = sugarResult;
    }

    public String getAboutSugar() {
        return aboutSugar;
    }

    public void setAboutSugar(String aboutSugar) {
        this.aboutSugar = aboutSugar;
    }
}

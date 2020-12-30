package com.arko.edc;

public class DataSetFireSug {
    String aboutSugar, date, sugarResult;

    public String getAboutSugar() {
        return aboutSugar;
    }

    public void setAboutSugar(String aboutSugar) {
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

    public DataSetFireSug() {
    }

    public DataSetFireSug(String aboutSugar, String date, String sugarResult) {
        this.aboutSugar = aboutSugar;
        this.date = date;
        this.sugarResult = sugarResult;
    }

}

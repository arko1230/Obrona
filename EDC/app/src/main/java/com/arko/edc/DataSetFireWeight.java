package com.arko.edc;

public class DataSetFireWeight {

    String weightResult,weightAbout,date;

    public String getWeightResult() {
        return weightResult;
    }

    public void setWeightResult(String weightResult) {
        this.weightResult = weightResult;
    }

    public String getWeightAbout() {
        return weightAbout;
    }

    public void setWeightAbout(String weightAbout) {
        this.weightAbout = weightAbout;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DataSetFireWeight() {
    }

    public DataSetFireWeight(String weightResult, String weightAbout, String date) {
        this.weightResult = weightResult;
        this.weightAbout = weightAbout;
        this.date = date;
    }
}

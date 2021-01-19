package com.arko.edc;

public class DataSetFireFelt {

    String fettleResult, fettleScale, fettleAbout,date;

    public String getFettleResult() {
        return fettleResult;
    }

    public void setFettleResult(String fettleResult) {
        this.fettleResult = fettleResult;
    }

    public String getFettleScale() {
        return fettleScale;
    }

    public void setFettleScale(String fettleScale) {
        this.fettleScale = fettleScale;
    }

    public String getFettleAbout() {
        return fettleAbout;
    }

    public void setFettleAbout(String fettleAbout) {
        this.fettleAbout = fettleAbout;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DataSetFireFelt() {
    }

    public DataSetFireFelt(String fettleResult, String fettleScale, String fettleAbout, String date) {
        this.fettleResult = fettleResult;
        this.fettleScale = fettleScale;
        this.fettleAbout = fettleAbout;
        this.date = date;
    }
}

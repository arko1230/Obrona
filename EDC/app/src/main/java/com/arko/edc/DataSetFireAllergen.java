package com.arko.edc;

public class DataSetFireAllergen {
    String allergenName,allergenFactor,allergenShows,allergenHide,date;

    public String getAllergenName() {
        return allergenName;
    }

    public void setAllergenName(String allergenName) {
        this.allergenName = allergenName;
    }

    public String getAllergenFactor() {
        return allergenFactor;
    }

    public void setAllergenFactor(String allergenFactor) {
        this.allergenFactor = allergenFactor;
    }

    public String getAllergenShows() {
        return allergenShows;
    }

    public void setAllergenShows(String allergenShows) {
        this.allergenShows = allergenShows;
    }

    public String getAllergenHide() {
        return allergenHide;
    }

    public void setAllergenHide(String allergenHide) {
        this.allergenHide = allergenHide;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    DataSetFireAllergen(){}

    public DataSetFireAllergen(String allergenName, String allergenFactor, String allergenShows, String allergenHide, String date) {
        this.allergenName = allergenName;
        this.allergenFactor = allergenFactor;
        this.allergenShows = allergenShows;
        this.allergenHide = allergenHide;
        this.date = date;
    }
}

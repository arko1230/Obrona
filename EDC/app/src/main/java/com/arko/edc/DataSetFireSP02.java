package com.arko.edc;

public class DataSetFireSP02 {

    String oxygenResult,aboutOxygen,date;

    public String getOxygenResult() {
        return oxygenResult;
    }

    public void setOxygenResult(String oxygenResult) {
        this.oxygenResult = oxygenResult;
    }

    public String getAboutOxygen() {
        return aboutOxygen;
    }

    public void setAboutOxygen(String aboutOxygen) {
        this.aboutOxygen = aboutOxygen;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DataSetFireSP02() {
    }

    public DataSetFireSP02(String oxygenResult, String aboutOxygen, String date) {
        this.oxygenResult = oxygenResult;
        this.aboutOxygen = aboutOxygen;
        this.date = date;
    }
}

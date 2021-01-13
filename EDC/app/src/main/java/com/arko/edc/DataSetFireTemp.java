package com.arko.edc;

public class DataSetFireTemp {

    String data;
    String temp_result;
    String temp_about;

    public DataSetFireTemp(String data, String temp_result, String temp_about) {
        this.data = data;
        this.temp_result = temp_result;
        this.temp_about = temp_about;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTemp_result() {
        return temp_result;
    }

    public void setTemp_result(String temp_result) {
        this.temp_result = temp_result;
    }

    public String getTemp_about() {
        return temp_about;
    }

    public void setTemp_about(String temp_about) {
        this.temp_about = temp_about;
    }
}

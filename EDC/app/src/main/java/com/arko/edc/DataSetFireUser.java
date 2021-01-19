package com.arko.edc;

import android.widget.EditText;

public class DataSetFireUser {

    private String blood,e_txtUserName,e_txtAge;


    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getE_txtUserName() {
        return e_txtUserName;
    }

    public void setE_txtUserName(String e_txtUserName) {
        this.e_txtUserName = e_txtUserName;
    }

    public String getE_txtAge() {
        return e_txtAge;
    }

    public void setE_txtAge(String e_txtAge) {
        this.e_txtAge = e_txtAge;
    }

    public DataSetFireUser(){

    }

    public DataSetFireUser(String blood, String e_txtUserName, String e_txtAge) {
        this.blood = blood;
        this.e_txtUserName = e_txtUserName;
        this.e_txtAge = e_txtAge;
    }
}

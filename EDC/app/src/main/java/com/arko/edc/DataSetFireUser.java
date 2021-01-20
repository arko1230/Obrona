package com.arko.edc;

public class DataSetFireUser {

    String userAge, userBlood, userMale, userName;

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserBlood() {
        return userBlood;
    }

    public void setUserBlood(String userBlood) {
        this.userBlood = userBlood;
    }

    public String getUserMale() {
        return userMale;
    }

    public void setUserMale(String userMale) {
        this.userMale = userMale;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public DataSetFireUser() {
    }

    public DataSetFireUser(String userAge, String userBlood, String userMale, String userName) {
        this.userAge = userAge;
        this.userBlood = userBlood;
        this.userMale = userMale;
        this.userName = userName;
    }
}

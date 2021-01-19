package com.arko.edc;

public class DataSetFireDoctor {

    String doctorName,doctorType,doctorAbout,doctorContakt;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public String getDoctorAbout() {
        return doctorAbout;
    }

    public void setDoctorAbout(String doctorAbout) {
        this.doctorAbout = doctorAbout;
    }

    public String getDoctorContakt() {
        return doctorContakt;
    }

    public void setDoctorContakt(String doctorContakt) {
        this.doctorContakt = doctorContakt;
    }

    DataSetFireDoctor(){}

    public DataSetFireDoctor(String doctorName, String doctorType, String doctorAbout, String doctorContakt) {
        this.doctorName = doctorName;
        this.doctorType = doctorType;
        this.doctorAbout = doctorAbout;
        this.doctorContakt = doctorContakt;
    }
}

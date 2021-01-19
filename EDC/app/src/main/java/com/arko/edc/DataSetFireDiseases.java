package com.arko.edc;

public class DataSetFireDiseases {

    String MainDiseaseName,Time_disease, TimeRecovery,Symptoms, Procedure;

    public String getMainDiseaseName() {
        return MainDiseaseName;
    }

    public void setMainDiseaseName(String mainDiseaseName) {
        MainDiseaseName = mainDiseaseName;
    }

    public String getTime_disease() {
        return Time_disease;
    }

    public void setTime_disease(String time_disease) {
        Time_disease = time_disease;
    }

    public String getTimeRecovery() {
        return TimeRecovery;
    }

    public void setTimeRecovery(String timeRecovery) {
        TimeRecovery = timeRecovery;
    }

    public String getSymptoms() {
        return Symptoms;
    }

    public void setSymptoms(String symptoms) {
        Symptoms = symptoms;
    }

    public String getProcedure() {
        return Procedure;
    }

    public void setProcedure(String procedure) {
        Procedure = procedure;
    }

    public DataSetFireDiseases() {
    }

    public DataSetFireDiseases(String mainDiseaseName, String time_disease, String timeRecovery, String symptoms, String procedure) {
        MainDiseaseName = mainDiseaseName;
        Time_disease = time_disease;
        TimeRecovery = timeRecovery;
        Symptoms = symptoms;
        Procedure = procedure;
    }
}

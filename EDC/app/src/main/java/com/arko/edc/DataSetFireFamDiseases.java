package com.arko.edc;

public class DataSetFireFamDiseases {

    String DiseaseName, Who_disease,ProgressDisease,DrugsDisease,DiseaseInfoExtra;

    public String getDiseaseName() {
        return DiseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        DiseaseName = diseaseName;
    }

    public String getWho_disease() {
        return Who_disease;
    }

    public void setWho_disease(String who_disease) {
        Who_disease = who_disease;
    }

    public String getProgressDisease() {
        return ProgressDisease;
    }

    public void setProgressDisease(String progressDisease) {
        ProgressDisease = progressDisease;
    }

    public String getDrugsDisease() {
        return DrugsDisease;
    }

    public void setDrugsDisease(String drugsDisease) {
        DrugsDisease = drugsDisease;
    }

    public String getDiseaseInfoExtra() {
        return DiseaseInfoExtra;
    }

    public void setDiseaseInfoExtra(String diseaseInfoExtra) {
        DiseaseInfoExtra = diseaseInfoExtra;
    }

    public DataSetFireFamDiseases() {
    }

    public DataSetFireFamDiseases(String diseaseName, String who_disease, String progressDisease, String drugsDisease, String diseaseInfoExtra) {
        DiseaseName = diseaseName;
        Who_disease = who_disease;
        ProgressDisease = progressDisease;
        DrugsDisease = drugsDisease;
        DiseaseInfoExtra = diseaseInfoExtra;
    }
}

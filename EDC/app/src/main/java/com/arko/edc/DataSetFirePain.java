package com.arko.edc;

public class DataSetFirePain {

    String PainPlace,Pain_leve,Pain_metod,When_Pain,When_Pain_start,When_Pain_end;

    public String getPainPlace() {
        return PainPlace;
    }

    public void setPainPlace(String painPlace) {
        PainPlace = painPlace;
    }

    public String getPain_leve() {
        return Pain_leve;
    }

    public void setPain_leve(String pain_leve) {
        Pain_leve = pain_leve;
    }

    public String getPain_metod() {
        return Pain_metod;
    }

    public void setPain_metod(String pain_metod) {
        Pain_metod = pain_metod;
    }

    public String getWhen_Pain() {
        return When_Pain;
    }

    public void setWhen_Pain(String when_Pain) {
        When_Pain = when_Pain;
    }

    public String getWhen_Pain_start() {
        return When_Pain_start;
    }

    public void setWhen_Pain_start(String when_Pain_start) {
        When_Pain_start = when_Pain_start;
    }

    public String getWhen_Pain_end() {
        return When_Pain_end;
    }

    public void setWhen_Pain_end(String when_Pain_end) {
        When_Pain_end = when_Pain_end;
    }

    public DataSetFirePain() {
    }

    public DataSetFirePain(String painPlace, String pain_leve, String pain_metod, String when_Pain, String when_Pain_start, String when_Pain_end) {
        PainPlace = painPlace;
        Pain_leve = pain_leve;
        Pain_metod = pain_metod;
        When_Pain = when_Pain;
        When_Pain_start = when_Pain_start;
        When_Pain_end = when_Pain_end;
    }
}

package com.arko.edc;

public class DataSetFireNote {

    String noteTitle, aboutNote, date;

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getAboutNote() {
        return aboutNote;
    }

    public void setAboutNote(String aboutNote) {
        this.aboutNote = aboutNote;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DataSetFireNote() {
    }

    public DataSetFireNote(String noteTitle, String aboutNote, String date) {
        this.noteTitle = noteTitle;
        this.aboutNote = aboutNote;
        this.date = date;
    }
}

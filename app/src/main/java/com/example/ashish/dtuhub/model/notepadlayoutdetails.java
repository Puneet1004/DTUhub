package com.example.ashish.dtuhub.model;

public class notepadlayoutdetails {

    public String notetext;
    public long notedate;

    public notepadlayoutdetails(String notetext, long notedate) {
        this.notetext = notetext;
        this.notedate = notedate;
    }

    public String getNotetext() {
        return notetext;
    }

    public void setNotetext(String notetext) {
        this.notetext = notetext;
    }

    public long getNotedate() {
        return notedate;
    }

    public void setNotedate(long notedate) {
        this.notedate = notedate;
    }
}

package com.example.anamika.androidqrcodescanner;

public class FacultyObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    private String subjectCode;
    private String dateTime;
    public FacultyObject(String name, String subjectCode, String dateTime) {
        this.name = name;
        this.subjectCode = subjectCode;
        this.dateTime = dateTime;
    }

}

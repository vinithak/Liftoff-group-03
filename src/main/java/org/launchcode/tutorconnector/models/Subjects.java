package org.launchcode.tutorconnector.models;

public enum Subjects {

    MATH("Math"),
    ENGLISH("English"),
    SCIENCE("Science"),
    ARTS("Arts"),
    MUSIC("Music");

    private final String displaySubject;

    Subjects(String displaySubject) {
        this.displaySubject = displaySubject;
    }

    public String getDisplaySubject() {
        return displaySubject;
    }
}

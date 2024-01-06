package org.launchcode.tutorconnector.models;

public enum Subjects {

    MATH("Math"),
    ENGLISH("English"),
    SCIENCE("Science"),
    ARTS("Arts"),
    MUSIC("Music");

    private final String displaySubjects;

    Subjects(String displaySubjects) {
        this.displaySubjects = displaySubjects;
    }

    public String getDisplaySubjects() {
        return displaySubjects;
    }
}

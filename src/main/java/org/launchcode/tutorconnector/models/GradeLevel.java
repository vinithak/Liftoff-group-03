package org.launchcode.tutorconnector.models;

public enum GradeLevel {

    ELEMENTARY("Elementary School"),
    MIDDLE("Middle School"),
    HIGH("High School"),
    COLLEGE("College");

    private final String displayGradeLevel;

    GradeLevel(String displayGradeLevel) {
        this.displayGradeLevel = displayGradeLevel;
    }

    public String getDisplayGradeLevel() {
        return displayGradeLevel;
    }
}

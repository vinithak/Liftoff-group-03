package org.launchcode.tutorconnector.models;

public enum TimeZone {

    CENTRAL("Central"),
    EASTERN("Eastern"),
    MOUNTAIN("Mountain"),
    PACIFIC("Pacific");

    private final String displayTimeZone;

    TimeZone(String displayTimeZone) {
        this.displayTimeZone = displayTimeZone;
    }

    public String getDisplayTimeZone() {
        return displayTimeZone;
    }
}

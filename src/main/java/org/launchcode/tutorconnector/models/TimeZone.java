package org.launchcode.tutorconnector.models;

public enum TimeZone {
    //fixed set of timezones
    EASTERN_TIMEZONE("EST"),
    CENTRAL_TIMEZONE("CST"),
    MOUNTAIN_TIMEZONE("MST"),
    PACIFIC_TIMEZONE("PST");

    private final String displayZone;

    TimeZone(String displayZone) {
        this.displayZone = displayZone;
    }

    public String getDisplayZone() {
        return displayZone;
    }
}
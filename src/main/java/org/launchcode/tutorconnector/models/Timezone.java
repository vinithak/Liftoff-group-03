package org.launchcode.tutorconnector.models;

public enum Timezone {

    CENTRAL("Central"),
    EASTERN("Eastern"),
    MOUNTAIN("Mountain"),
    PACIFIC("Pacific");

    private final String displayTimeZone;

    Timezone(String displayTimeZone) {
        this.displayTimeZone = displayTimeZone;
    }

    public String getDisplayTimeZone() {
        return displayTimeZone;
    }
}

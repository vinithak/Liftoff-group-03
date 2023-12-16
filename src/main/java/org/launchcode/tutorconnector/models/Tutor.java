package org.launchcode.tutorconnector.models;

import jakarta.persistence.Entity;

import java.util.ArrayList;

@Entity
public class Tutor extends AbstractEntity {

    private ArrayList<String> qualifications;
    private ArrayList<String> subjects;

    private String availability;

    public Tutor (){}

    public Tutor(String firstName, String lastName, String email, String password, TimeZone timeZone, ArrayList<String> qualifications, ArrayList<String> subjects, String availability) {
        super(firstName, lastName, email, password, timeZone);
        this.qualifications = qualifications;
        this.subjects = subjects;
        this.availability = availability;
    }


    public ArrayList<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(ArrayList<String> qualifications) {
        this.qualifications = qualifications;
    }

    public ArrayList<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<String> subjects) {
        this.subjects = subjects;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}

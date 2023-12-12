package org.launchcode.tutorconnector.models;

import jakarta.persistence.Entity;

import java.util.ArrayList;

@Entity
public class Tutor extends AbstractEntity {

    private ArrayList<String> qualifications;
    private ArrayList<String> subjects;

    private String availability;

    public Tutor (){}
    public Tutor(String email, String password) {
        this.email = email;
        this.pwHash = encoder.encode(password);
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

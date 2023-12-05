package org.launchcode.tutorconnector.models;

import jakarta.persistence.Entity;

@Entity
public class Tutor extends AbstractEntity {

    private String qualifications;

    private String subjects;

    private String availability;

    public Tutor (){}
    public Tutor(String email, String password) {
        this.email = email;
        this.pwHash = encoder.encode(password);
    }

    public String getQualifications() {
        return qualifications;
    }

    public String getSubjects() {
        return subjects;
    }

    public String getAvailability() {
        return availability;
    }
}

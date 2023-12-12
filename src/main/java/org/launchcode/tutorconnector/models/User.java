package org.launchcode.tutorconnector.models;

import jakarta.persistence.Entity;

@Entity
public class User extends AbstractEntity {

    private String gradeLevel;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.pwHash = encoder.encode(password);
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
}

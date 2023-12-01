package org.launchcode.tutorconnector.models;

import jakarta.persistence.Entity;

@Entity
public class Tutor extends AbstractEntity {

    public Tutor (){}
    public Tutor(String email, String password) {
        this.email = email;
        this.pwHash = encoder.encode(password);
    }

}

package org.launchcode.tutorconnector.models;

import jakarta.persistence.Entity;

@Entity
public class User extends AbstractEntity {

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.pwHash = encoder.encode(password);
    }
}

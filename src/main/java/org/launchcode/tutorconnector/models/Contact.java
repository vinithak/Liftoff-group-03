package org.launchcode.tutorconnector.models;

import jakarta.persistence.Entity;

@Entity
public class Contact extends AbstractEntity {

    private String message;

    public Contact() {
    }

    public Contact(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

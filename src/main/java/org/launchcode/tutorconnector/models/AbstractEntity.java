package org.launchcode.tutorconnector.models;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;


@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @Email(message = "Invalid email. Try again.")
    private String email;

    @NotNull(message = "image is required")
    @Size(min = 30, max = 150, message = "image path must be between 3 and 100 characters long")
    private String imagePath;

    @NotNull
    private TimeZone timeZone;

    //empty constructor pass down to the form for structure of object
    public AbstractEntity() {}

    // actual constructor used to instantiate an object
    public AbstractEntity(String firstName, String lastName, String email, String password, TimeZone timeZone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.timeZone = timeZone;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }


    @Override
    public String toString() {
        return "AbstractEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", timeZone=" + timeZone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
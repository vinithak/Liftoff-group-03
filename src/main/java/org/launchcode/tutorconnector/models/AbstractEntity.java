package org.launchcode.tutorconnector.models;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;
import java.util.TimeZone;

import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


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

    @NotBlank
    @NotNull
    String password;

    @NotBlank
    @NotNull
    String pwHash;

    @NotNull
    @NotBlank
    private TimeZone timeZone;

    //empty constructor pass down to the form for structure of object
    public AbstractEntity() {}
    // actual constructor used to instantiate an object

    public AbstractEntity(String firstName, String lastName, String email, String password, TimeZone timeZone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pwHash = encoder.encode(password);
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

    static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPwHash() {
        return pwHash;
    }

    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
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

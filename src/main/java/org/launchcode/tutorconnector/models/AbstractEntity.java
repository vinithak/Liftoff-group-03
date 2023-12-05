package org.launchcode.tutorconnector.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @Email String email;

    @NotBlank
    @NotNull
    String pwHash;

    private String timeZone;

    //empty constructor pass down to the form for structure of object
    public AbstractEntity() {}
    // actual constructor used to instantiate an object

    public AbstractEntity(String firstName, String lastName, String email, String password, String timeZone) {
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

    public String getTimeZone() {
        return timeZone;
    }

    static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
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

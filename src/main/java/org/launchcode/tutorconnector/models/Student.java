package org.launchcode.tutorconnector.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student extends AbstractEntity{

    private GradeLevel gradeLevel;

    @ManyToMany(mappedBy = "students")
    private List<Tutor> tutors = new ArrayList<>();

    @NotBlank
    @NotNull
    private String pwHash;

    static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Student() {}

    public Student(String firstName, String lastName, String email, String password, String pwHash, GradeLevel gradeLevel) {
        super(firstName, lastName, email, password);
        this.gradeLevel = gradeLevel;
        this.pwHash = encoder.encode(password);
    }

    public Student(String email, String password) {
    }

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(GradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public List<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(List<Tutor> tutors) {
        this.tutors = tutors;
    }

    public String getPwHash() {
        return pwHash;
    }

    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }


}
package org.launchcode.tutorconnector.models.dto;

import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.launchcode.tutorconnector.models.Subject;
import org.launchcode.tutorconnector.models.Subjects;

import java.util.ArrayList;
import java.util.List;

public class RegistrationFormDTO extends LoginFormDTO {

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8-20 characters")
    @Pattern(regexp = "^(?=.*[!@#$%^&*()-+=]).+$", message = "Password must contain at least one special character")
    @Pattern(regexp = "^(?=.*[A-Z]).*$", message = "Password must contain at least one uppercase letter")
    private static String verifyPassword;

    private Subjects subjects;

    private ArrayList<String> qualifications;


    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        RegistrationFormDTO.verifyPassword = verifyPassword;
    }

    public ArrayList<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(ArrayList<String> qualifications) {
        this.qualifications = qualifications;
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }
}

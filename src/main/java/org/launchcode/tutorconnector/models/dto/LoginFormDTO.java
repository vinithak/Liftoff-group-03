package org.launchcode.tutorconnector.models.dto;

import jakarta.validation.constraints.*;

public class LoginFormDTO {

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8-20 characters")
    @Pattern(regexp = "^(?=.*[!@#$%^&*()-+=]).+$", message = "Password must contain at least one special character")
    @Pattern(regexp = "^(?=.*[A-Z]).*$", message = "Password must contain at least one uppercase letter")
    private String password;

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package org.launchcode.tutorconnector.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.tutorconnector.models.Tutor;
import org.launchcode.tutorconnector.models.data.StudentRepository;
import org.launchcode.tutorconnector.models.Student;
import org.launchcode.tutorconnector.models.dto.LoginFormDTO;
import org.launchcode.tutorconnector.models.dto.RegistrationFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("/student")
public class StudentAuthController {

    @Autowired
    private StudentRepository studentRepository;


    private static final String studentSessionKey = "student";

    private static void setStudentInSession(HttpSession session, Student student) {
        session.setAttribute(studentSessionKey, student.getId());
    }

    public Student getStudentFromSession(HttpSession session) {

        Integer studentId = (Integer) session.getAttribute(studentSessionKey);
        if (studentId == null) {
            return null;
        }

        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            return null;
        }

        return studentOpt.get();
    }

    @GetMapping("/register")
    public String displayRegistrationForm(Model model, HttpSession session) {
        model.addAttribute(new RegistrationFormDTO());
        //Send value of logged in boolean
        model.addAttribute("loggedIn", session.getAttribute("student") !=null);
        return "student/register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegistrationFormDTO registrationFormDTO, Errors errors, HttpServletRequest request) {
        // Send user back to form if errors are found

        if (errors.hasErrors()) {
            return "student/register";
        }
        // Send user back if email already exists
        Student existingStudent = studentRepository.findByEmail(registrationFormDTO.getEmail());

        if (existingStudent != null) {
            errors.rejectValue("email", "email.alreadyExists", "An account with that email already exists.");
            return "student/register";
        }
        // Send user back if passwords don't match
        String password = registrationFormDTO.getPassword();
        String verifyPassword = registrationFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            return "student/register";
        }
        //If no errors, save new email and password, start new session, redirect to userprofile
        Student newStudent = new Student(registrationFormDTO.getEmail(), registrationFormDTO.getPassword());
            newStudent.setFirstName(registrationFormDTO.getFirstName()); // Set the first name from the DTO
            newStudent.setLastName(registrationFormDTO.getLastName());   // Set the last name from the DTO
            newStudent.setPwHash(registrationFormDTO.getPassword());     // Set the password hash from the DTO
            newStudent.setEmail(registrationFormDTO.getEmail());         // Set the email from the DTO
        studentRepository.save(newStudent);
        setStudentInSession(request.getSession(), newStudent);
        return "redirect:/student/profile";
    }

    //Login route
    @GetMapping("login")
    public String displayLoginForm(Model model, HttpSession session) {
        model.addAttribute(new LoginFormDTO()); //loginFormDTO
        //SEND VALUE OF LOGGEDIN BOOLEAN
        model.addAttribute("loggedIn", session.getAttribute("student") !=null);
        return "login";
    }

    @PostMapping("login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "login";
        }
        Student theStudent = studentRepository.findByEmail(loginFormDTO.getEmail());

        String password = loginFormDTO.getPassword();
        //check both. security through obscurity
        if(theStudent == null || !theStudent.isMatchingPassword(password)) {
            errors.rejectValue("password",
                    "Login.invalid",
                    "Incorrect email/password. Please try again."
            );
            return "login";
        }
        setStudentInSession(request.getSession(), theStudent);
        return "redirect:/student-profile";
    }

//    Logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index";
    }


}

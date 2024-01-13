package org.launchcode.tutorconnector.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.tutorconnector.models.Contact;
import org.launchcode.tutorconnector.models.Login;
import org.launchcode.tutorconnector.models.Tutor;
import org.launchcode.tutorconnector.models.data.LoginRepository;
import org.launchcode.tutorconnector.models.data.StudentRepository;
import org.launchcode.tutorconnector.models.Student;
import org.launchcode.tutorconnector.models.dto.LoginFormDTO;
import org.launchcode.tutorconnector.models.dto.RegistrationFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LoginRepository loginRepository;


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
//      model.addAttribute(new Login());
        //Send value of logged in boolean
        model.addAttribute("studentLoggedIn", session.getAttribute("student") !=null);
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
        Student newStudent = new Student(registrationFormDTO.getEmail(), bCryptPasswordEncoder.encode(registrationFormDTO.getPassword()));
            newStudent.setFirstName(registrationFormDTO.getFirstName());
            newStudent.setLastName(registrationFormDTO.getLastName());
            newStudent.setPwHash(registrationFormDTO.getPassword());
            newStudent.setEmail(registrationFormDTO.getEmail());

        Login newLogin = new Login(registrationFormDTO.getEmail());
            newLogin.setEmail(registrationFormDTO.getEmail());
            newLogin.setRole("student");

        studentRepository.save(newStudent);
        loginRepository.save(newLogin);
        setStudentInSession(request.getSession(), newStudent);
        return "redirect:/student/profile/" + newStudent.getId();
    }


}

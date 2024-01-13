package org.launchcode.tutorconnector.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.tutorconnector.models.Login;
import org.launchcode.tutorconnector.models.Student;
import org.launchcode.tutorconnector.models.Tutor;
import org.launchcode.tutorconnector.models.data.LoginRepository;
import org.launchcode.tutorconnector.models.data.StudentRepository;
import org.launchcode.tutorconnector.models.data.TutorRepository;
import org.launchcode.tutorconnector.models.dto.LoginFormDTO;
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
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private TutorRepository tutorRepository;


    //Student
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

    //Tutor
    //the key to store user IDs
    private static final String tutorSessionKey = "tutor";

    //Stores key/value pair with session key and user ID
    private static void setTutorInSession(HttpSession session, Tutor tutor) {
        session.setAttribute(tutorSessionKey, tutor.getId());
    }

    //look up user with key
    public Tutor getTutorFromSession(HttpSession session) {

        Integer tutorId = (Integer) session.getAttribute(tutorSessionKey);
        if(tutorId == null) {
            return null;
        }

        Optional<Tutor> tutorOpt = tutorRepository.findById(tutorId);

        if (tutorOpt.isEmpty()) {
            return null;
        }

        return tutorOpt.get();
    }


    //Login route
    @GetMapping
    public String displayLoginForm(Model model, HttpSession session) {
        model.addAttribute(new LoginFormDTO()); //loginFormDTO
        // Send value of logged in boolean
        model.addAttribute("studentLoggedIn", session.getAttribute("student") !=null);
        model.addAttribute("tutorLoggedIn", session.getAttribute("tutor") !=null);
        return "login";
    }

    @PostMapping
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "login";
        }

        Tutor theTutor = tutorRepository.findByEmail(loginFormDTO.getEmail());
        Student theStudent = studentRepository.findByEmail(loginFormDTO.getEmail());

        String password = loginFormDTO.getPassword();


        // Check if the email exists in either the tutor or student table
        if (theTutor == null && theStudent == null) {
            errors.rejectValue("email",
                    "login.invalid",
                    "Incorrect email/password. Please try again."
            );
            return "login";
        }

        // Check the password for the found user
        if (theTutor != null && !theTutor.isMatchingPassword(password)) {
            errors.rejectValue("password",
                    "login.invalid",
                    "Incorrect email/password. Please try again."
            );
            return "login";
        }

        if (theStudent != null && !theStudent.isMatchingPassword(password)) {
            errors.rejectValue("password",
                    "login.invalid",
                    "Incorrect email/password. Please try again."
            );
            return "login";
        }

        // Route to profile by role

        if (theTutor != null) {
            setTutorInSession(request.getSession(), theTutor);
            return "redirect:/tutor/profile/" + theTutor.getId();
        } else {
            setStudentInSession(request.getSession(), theStudent);
            return "redirect:/student/profile/" + theStudent.getId();
        }

    }

    //    Logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

}

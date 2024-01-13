package org.launchcode.tutorconnector.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.tutorconnector.models.Login;
import org.launchcode.tutorconnector.models.Subjects;
import org.launchcode.tutorconnector.models.data.LoginRepository;
import org.launchcode.tutorconnector.models.data.TutorRepository;
import org.launchcode.tutorconnector.models.Tutor;
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

import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/tutor")
public class TutorAuthController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private LoginRepository loginRepository;

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

    @GetMapping("/register")
    public String displayRegistrationForm(Model model, HttpSession session) {
        model.addAttribute(new RegistrationFormDTO());
        // Send value of logged in boolean
        model.addAttribute("tutorLoggedIn", session.getAttribute("tutor") !=null);
        return "tutor/register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegistrationFormDTO registrationFormDTO, Errors errors, HttpServletRequest request) {

        // Send tutor back to form if errors are found
        if(errors.hasErrors()) {
            return "tutor/register";
        }
        // Send tutor back if email already exists
        Tutor existingTutor = tutorRepository.findByEmail(registrationFormDTO.getEmail());

        if(existingTutor != null) {
            errors.rejectValue("email", "email.alreadyExists", "An account with that email already exists.");
            return "tutor/register";
        }
        // Send tutor back if passwords don't match
        String password = registrationFormDTO.getPassword();
        String verifyPassword = registrationFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            return "tutor/register";
        }
        //If no errors, save new email and password, start new session, redirect to tutor profile
        Tutor newTutor = new Tutor(registrationFormDTO.getEmail(), bCryptPasswordEncoder.encode(registrationFormDTO.getPassword()));
            newTutor.setFirstName(registrationFormDTO.getFirstName());
            newTutor.setLastName(registrationFormDTO.getLastName());
            newTutor.setPwHash(registrationFormDTO.getPassword());
            newTutor.setEmail(registrationFormDTO.getEmail());
            newTutor.setQualifications(registrationFormDTO.getQualifications());



        Login newLogin = new Login(registrationFormDTO.getEmail());
            newLogin.setEmail(registrationFormDTO.getEmail());
            newLogin.setRole("tutor");

        tutorRepository.save(newTutor);
        loginRepository.save(newLogin);
        setTutorInSession(request.getSession(), newTutor);
        return "redirect:/tutor/profile/" + newTutor.getId();
    }

}

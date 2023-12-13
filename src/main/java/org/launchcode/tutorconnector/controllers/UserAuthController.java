package org.launchcode.tutorconnector.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.launchcode.tutorconnector.models.dto.LoginFormDTO;
import org.launchcode.tutorconnector.models.dto.RegistrationFormDTO;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import org.launchcode.tutorconnector.data.UserRepository;
import org.launchcode.tutorconnector.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserAuthController {

    @Autowired
    private UserRepository userRepository;

    private static final String userSessionKey = "user";

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    public User getUserFromSession(HttpSession session) {

        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return null;
        }

        return userOpt.get();
    }

    @GetMapping("/user/register")
    public String displayRegistrationForm(Model model, HttpSession session) {
        model.addAttribute(new RegistrationFormDTO());
        //Send value of logged in boolean
        model.addAttribute("loggedIn", session.getAttribute("user") !=null);
        return "register";
    }

    @PostMapping("/user/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegistrationFormDTO registrationFormDTO, Errors errors, HttpServletRequest request) {
        // Send user back to form if errors are found

        if (errors.hasErrors()) {
            return "register";
        }
        // Send user back if email already exists
        User existingUser = userRepository.findByEmail(registrationFormDTO.getEmail());

        if (existingUser != null) {
            errors.rejectValue("email", "email.alreadyExists", "An account with that email already exists.");
            return "register";
        }
        // Send user back if passwords don't match
        String password = registrationFormDTO.getPassword();
        String verifyPassword = registrationFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            return "register";
        }
        //If no errors, save new email and password, start new session, redirect to userprofile
        User newUser = new User(registrationFormDTO.getEmail(), registrationFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);
        return "redirect:/user-profile";
    }

    //Login route
    @GetMapping("/user/login")
    public String displayLoginForm(Model model, HttpSession session) {
        model.addAttribute(new LoginFormDTO()); //loginFormDTO
        //SEND VALUE OF LOGGEDIN BOOLEAN
        model.addAttribute("loggedIn", session.getAttribute("user") !=null);
        return "login";
    }

    @PostMapping("/user/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "login";
        }
        User theUser = userRepository.findByEmail(loginFormDTO.getEmail());

        String password = loginFormDTO.getPassword();
        //check both. security through obscurity
        if(theUser == null || !theUser.isMatchingPassword(password)) {
            errors.rejectValue("password",
                    "Login.invalid",
                         "Incorrect email/password. Please try again."
            );
            return "login";
        }
        setUserInSession(request.getSession(), theUser);
        return "redirect:/user-profile";
    }

    //Logout
    @GetMapping("/tutor/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

}

package org.launchcode.tutorconnector.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private LoginController loginController;

    @ModelAttribute
    public void globalAttributes(Model model, HttpSession session) {
        model.addAttribute("studentLoggedIn", loginController.getStudentFromSession(session) != null);
        model.addAttribute("tutorLoggedIn", loginController.getTutorFromSession(session) != null);
    }


}

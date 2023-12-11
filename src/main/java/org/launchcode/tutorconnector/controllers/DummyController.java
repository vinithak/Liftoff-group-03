package org.launchcode.tutorconnector.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@Controller
public class DummyController {

    @RequestMapping
    public String renderStudentRegisterForm() {
        return "student-register-form";
    }
}

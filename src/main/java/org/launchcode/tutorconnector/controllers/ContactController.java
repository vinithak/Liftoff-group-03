package org.launchcode.tutorconnector.controllers;

import org.launchcode.tutorconnector.models.data.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("contact")
public class ContactController {

    @Autowired
    ContactRepository cr;
}

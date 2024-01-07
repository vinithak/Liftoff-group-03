package org.launchcode.tutorconnector.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalendarViewController {
    @GetMapping("/calendar")
    public String displayCalendar(){
        return "calendar";

    }

    @PostMapping("/calendar")
    public void processCalendar(Model model)  {

        //  return "calendar";
    }

}

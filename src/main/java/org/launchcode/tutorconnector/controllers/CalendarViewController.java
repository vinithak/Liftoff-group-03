package org.launchcode.tutorconnector.controllers;

import org.launchcode.tutorconnector.models.Tutor;
import org.launchcode.tutorconnector.models.data.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;



@Controller
public class CalendarViewController {

    @Autowired
    TutorRepository tutorRepository;

    //post mapping should send tutor id
//    @GetMapping("/calendar/{id}")
//    public String displayCalendar(@PathVariable int id, Model model){
//        Optional optTutor = tutorRepository.findById(id);
//        if (optTutor.isPresent()) {
//            Tutor tutor = (Tutor) optTutor.get();
//            model.addAttribute("tutor", tutor);
//        }
//        return "calendar";
//
//    }


//    @GetMapping("/calendar")
//    public String displayCalendar(Model model,@RequestParam int tutorId){
//        Optional optTutor = tutorRepository.findById(tutorId);
//        if (optTutor.isPresent()) {
//            Tutor tutor = (Tutor) optTutor.get();
//            model.addAttribute("tutor", tutor);
//        }
// //       model.addAttribute("tutor", tutor);
//        return "calendar";
//
//    }

//    @PostMapping("/calendar")
//    public void processCalendar(Model model)  {
//
//        //  return "calendar";
//    }

}

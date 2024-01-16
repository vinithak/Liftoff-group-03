package org.launchcode.tutorconnector.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.tutorconnector.models.Student;
import org.launchcode.tutorconnector.models.Tutor;
import org.launchcode.tutorconnector.models.data.StudentRepository;
import org.launchcode.tutorconnector.models.data.SubjectRepository;
import org.launchcode.tutorconnector.models.data.TutorRepository;
import org.launchcode.tutorconnector.models.data.TutorReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
//@RequestMapping("tutor")
public class TutorController {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TutorReviewRepository tutorReviewRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @RequestMapping("/tutor")
    public String index(Model model) {

        model.addAttribute("title", "All Tutors");
        model.addAttribute("tutors", tutorRepository.findAll());
        return "tutor/index";
    }


    @GetMapping("tutor/profile/{id}")
    public String displayTutorProfile(@PathVariable int id, Model model) {
        Optional<Tutor> optTutor = tutorRepository.findById(id);
        if (optTutor.isPresent()) {
            Tutor tutor = (Tutor) optTutor.get();
            model.addAttribute("tutor", tutor);
            return "tutor/profile";
        } else {
            return "redirect:/register";
        }
    }
    @RequestMapping("/calendar")
    public String displayCalendar(Model model,@RequestParam String tutorId) {
        int value = Integer.parseInt(tutorId);
        Optional optTutor = tutorRepository.findById(value);
        if (optTutor.isPresent()) {
            Tutor tutor = (Tutor) optTutor.get();
            model.addAttribute("tutor", tutor);
            model.addAttribute("events", tutor.getEvents());
        }
        return "calendar";
    }

}

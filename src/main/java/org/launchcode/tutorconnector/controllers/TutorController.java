package org.launchcode.tutorconnector.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
@RequestMapping("tutor")
public class TutorController {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TutorReviewRepository tutorReviewRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "All Tutors");
        model.addAttribute("tutors", tutorRepository.findAll());
        return "index";
    }

    @GetMapping("/profile")
    public String displayProfile(Model model, HttpSession session) {
//        model.addAttribute(new Tutor);
//        model.addAttribute("loggedIn", session.getAttribute("tutor") !=null);
        return "tutor/profile";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Tutor");
        model.addAttribute(new Tutor());
        return "add";
    }

    @PostMapping("add")
    public String processAddTutorForm(@ModelAttribute @Valid Tutor newTutor,
                                    Errors errors, Model model, @RequestParam int tutorId) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Tutor");
            return "add";
        }
        tutorRepository.save(newTutor);
        return "redirect:";
    }

    @GetMapping("view/{tutorId}")
    public String displayViewJob(Model model, @PathVariable int tutorId) {

        Optional<Tutor> optTutor = tutorRepository.findById(tutorId);
        if (optTutor.isPresent()) {
            Tutor tutor = (Tutor) optTutor.get();
            model.addAttribute("tutor", tutor);
            return "view";
        }
        return "redirect:../";
    }


}

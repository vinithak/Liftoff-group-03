package org.launchcode.tutorconnector.controllers;
import org.launchcode.tutorconnector.models.Search;
import org.launchcode.tutorconnector.models.Tutor;
import org.launchcode.tutorconnector.models.data.StudentRepository;
import org.launchcode.tutorconnector.models.data.TutorRepository;
import org.launchcode.tutorconnector.models.data.TutorReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping("list")
public class ListController {



    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TutorReviewRepository tutorReviewRepository;

    static HashMap<String, String> filterChoices = new HashMap<>();

    public ListController () {

        filterChoices.put("all", "All");
        filterChoices.put("tutor", "Tutor");

    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("tutors",tutorRepository.findAll());
        return "list";
    }

    @RequestMapping(value = "tutors")
    public String listTutorsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Tutor> tutors;
        if (column.toLowerCase().equals("all")){
            tutors = tutorRepository.findAll();
            model.addAttribute("title", "All Tutors");
        } else {
            tutors = Search.findTutorsByColumnAndValue(column, value, tutorRepository.findAll());
            model.addAttribute("title", "Tutors with " + filterChoices.get(column) + ": " + value);
        }
        model.addAttribute("tutors", tutors);

        return "list-tutors";
    }

}

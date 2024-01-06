package org.launchcode.tutorconnector.controllers;
import org.launchcode.tutorconnector.models.Search;
import org.launchcode.tutorconnector.models.Tutor;
import org.launchcode.tutorconnector.models.data.TutorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.launchcode.tutorconnector.controllers.ListController.filterChoices;

@Controller
@RequestMapping("search")
public class SearchController {


    private TutorRepository tutorRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("filters", filterChoices);
        return "search";
    }


    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Tutor> tutors;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            tutors = tutorRepository.findAll();
        } else {
            tutors = Search.findTutorsByColumnAndValue(searchType, searchTerm, tutorRepository.findAll());
        }
        model.addAttribute("filters", filterChoices);
        model.addAttribute("title", "Jobs with " + filterChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("tutors", tutors);

        return "search";
    }

}

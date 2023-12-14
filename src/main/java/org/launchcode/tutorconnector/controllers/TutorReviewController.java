package org.launchcode.tutorconnector.controllers;

import jakarta.validation.Valid;
import org.launchcode.tutorconnector.models.TutorReview;
import org.launchcode.tutorconnector.models.data.TutorReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("tutorreviews")
public class TutorReviewController {
    @Autowired
    private TutorReviewRepository tutorReviewRepository;


    @GetMapping("add")
    public String displayAddReviewForm(Model model) {
        model.addAttribute(new TutorReview());
        return "tutorreviews/add";
    }


    @PostMapping("add")
    public String processAddTutorReviewform(@ModelAttribute @Valid TutorReview newTutorReview,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "tutorreviews/add";
        }
        tutorReviewRepository.save(newTutorReview);
        return "redirect:";
    }

    @GetMapping("view/{tutorId}")
    public String displayViewTutorReviews(Model model, @PathVariable int tutorId) {

        Optional optTutorReview = tutorReviewRepository.findById(tutorId);
        if (optTutorReview.isPresent()) {
            TutorReview tutorReviews = (TutorReview) optTutorReview.get();
            model.addAttribute("tutorReviews", tutorReviews);
            return "tutorreviews/view";
        } else {
            return "redirect:../";
        }

    }

}

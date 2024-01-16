package org.launchcode.tutorconnector.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.tutorconnector.models.Student;
import org.launchcode.tutorconnector.models.data.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("students", studentRepository.findAll() );
        return "student/index";
    }

    @GetMapping("/profile/{id}")
    public String displayStudentProfile(@PathVariable int id, Model model) {
        Optional<Student> optStudent = studentRepository.findById(id);
        if (optStudent.isPresent()) {
            Student student = optStudent.get();
            model.addAttribute("student", student);
            return "student/profile";
        } else {
            return "redirect:/register";
        }
    }

//    @GetMapping("/profile/{id}/update")
//    public String displayUpdateStudentProfile(Model model){
//        model.addAttribute("students", studentRepository.findAll() );
//        return "student/index";
//    }
//
//    @GetMapping("/profile/{id}/update")
//    public String updateStudentProfile(@PathVariable int id, Model model) {
//        Optional<Student> optStudent = studentRepository.findById(id);
//        if (optStudent.isPresent()) {
//            Student student = optStudent.get();
//            model.addAttribute("student", student);
//            return "student/profile";
//        } else {
//            return "redirect:/profile/{id}";
//        }
//    }

}

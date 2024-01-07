package org.launchcode.tutorconnector.controllers;
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
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("students", studentRepository.findAll() );
        return "student/index";
    }



    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Student());
        return "student/add";
    }

    @PostMapping("add")
    public String processAddStudentForm(@ModelAttribute @Valid Student newStudent,
                                        Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "student/add";
        }
        studentRepository.save(newStudent);
        return "redirect:";
    }

    @GetMapping("view/{studentId}")
    public String displayViewSkill(Model model, @PathVariable int studentId) {

        Optional optStudent = studentRepository.findById(studentId);
        if (optStudent.isPresent()) {
            Student student = (Student) optStudent.get();
            model.addAttribute("student", student);
            return "student/view";
        } else {
            return "redirect:../";
        }

    }

}

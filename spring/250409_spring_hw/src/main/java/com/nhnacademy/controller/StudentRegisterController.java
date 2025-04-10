package com.nhnacademy.controller;

import com.nhnacademy.domain.Student;
import com.nhnacademy.exception.StudentAlreadyExistsException;
import com.nhnacademy.repository.StudentRepository;
import com.nhnacademy.validator.StudentRegisterRequestValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class StudentRegisterController {
    private final StudentRepository studentRepository;
    private final StudentRegisterRequestValidator validator;

    public StudentRegisterController(StudentRepository studentRepository, StudentRegisterRequestValidator validator) {
        this.studentRepository = studentRepository;
        this.validator = validator;
    }

    @GetMapping
    public String studentRegisterForm() {
        return "studentRegister";
    }

    @PostMapping
    public String registerStudent(@Validated @ModelAttribute Student student, Model model) {
        try {
            Student savedStudent = studentRepository.register(student);
            return "redirect:/student/" + savedStudent.getId() + "/modify";
        } catch (StudentAlreadyExistsException ex) {
            model.addAttribute("error", "This student ID is already taken.");
            return "studentRegister";
        }
    }

    @InitBinder("student")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }
}
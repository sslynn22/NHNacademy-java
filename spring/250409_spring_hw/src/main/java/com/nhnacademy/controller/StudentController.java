package com.nhnacademy.controller;

import com.nhnacademy.domain.Student;
import com.nhnacademy.exception.StudentNotFoundException;
import com.nhnacademy.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{studentId}")
    public String viewStudent(@PathVariable("studentId") String studentId,
                              @CookieValue(value = "JSESSIONID", required = false) String sessionCookie,
                              Model model) {
        if (!StringUtils.hasText(sessionCookie)) {
            return "redirect:/error";
        }

        Student student = studentRepository.getStudent(studentId);
        if (student == null) {
            return "redirect:/error";
        }

        Student maskedStudent = Student.constrictPasswordMaskedStudent(student);
        model.addAttribute("student", maskedStudent);
        return "studentView";
    }

    @GetMapping("/{studentId}/modify")
    public String studentModifyForm(@PathVariable("studentId") String studentId,
                                    @CookieValue(value = "JSESSIONID", required = false) String sessionCookie,
                                    HttpSession session,
                                    Model model) {
        if (!StringUtils.hasText(sessionCookie)) {
            return "redirect:/error";
        }

        Student student = studentRepository.getStudent(studentId);
        if (student == null) {
            return "redirect:/error";
        }

        model.addAttribute("student", student);
        return "studentModify";
    }

    @PostMapping("/{studentId}/modify")
    public String modifyStudent(@PathVariable String studentId,
                                @Valid @ModelAttribute Student student,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "studentModify";
        }
        studentRepository.update(studentId, student);
        Student updatedStudent = studentRepository.getStudent(studentId);
        model.addAttribute("student", updatedStudent);
        return "redirect:/student/" + updatedStudent.getId();
    }
}
package com.nhnacademy.controller;

import com.nhnacademy.repository.StudentRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final StudentRepository studentRepository;

    public LoginController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/login")
    public String login(@CookieValue(value = "JSESSIONID", required = false) String session,
                        Model model) {
        return "loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          ModelMap modelMap) {
        if (studentRepository.matches(id, pwd)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("loginId", id);

            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            modelMap.put("id", session.getId());
            return "redirect:/student/" + id;
        } else {
            modelMap.put("error", "login failed");
            return "loginForm";
        }
    }
}


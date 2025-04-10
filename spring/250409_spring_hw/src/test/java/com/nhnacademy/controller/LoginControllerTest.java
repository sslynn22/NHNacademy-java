package com.nhnacademy.controller;

import com.nhnacademy.domain.Student;
import com.nhnacademy.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @InjectMocks
    private LoginController loginController;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId("sslynn22");
        student.setPassword("qwer1234");
    }

    @Test
    void login() throws Exception {
        when(studentRepository.matches("sslynn22", "qwer1234")).thenReturn(true);

        mockMvc.perform(post("/login")
                        .param("id", "sslynn22")
                        .param("pwd", "qwer1234"))
                .andExpect(redirectedUrl("/student/sslynn22"));
    }

    @Test
    void doLogin_failure() throws Exception {
        when(studentRepository.matches("sslynn22", "qwerqwerqwerqwerqwer")).thenReturn(false);

        mockMvc.perform(post("/login")
                        .param("id", "sslynn22")
                        .param("pwd", "qwerqwerqwerqwerqwer"))
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("loginForm"));
    }
}
package com.nhnacademy.controller;

import com.nhnacademy.domain.Student;
import com.nhnacademy.repository.StudentRepository;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentController studentController;

    private MockHttpSession session;
    private Student student;

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        student = new Student("sslynn22", "seoin", "qwer1234", "sslynn22@naver.com", 100, "abc");
    }

    @Test
    void viewStudentWithoutSession() throws Exception {
        mockMvc.perform(get("/student/{studentId}", "testId"))
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void viewStudentWithInvalidStudent() throws Exception {
        when(studentRepository.getStudent("testId")).thenReturn(null);

        mockMvc.perform(get("/student/{studentId}", "testId")
                        .cookie(new Cookie("JSESSIONID", "invalidSession")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void studentModifyFormWithInvalidStudent() throws Exception {
        when(studentRepository.getStudent("testId")).thenReturn(null);

        mockMvc.perform(get("/student/{studentId}/modify", "testId")
                        .cookie(new Cookie("JSESSIONID", "validSession")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void studentModifyFormWithoutSession() throws Exception {
        mockMvc.perform(get("/student/{studentId}/modify", "testId"))
                .andExpect(redirectedUrl("/login"));
    }
}
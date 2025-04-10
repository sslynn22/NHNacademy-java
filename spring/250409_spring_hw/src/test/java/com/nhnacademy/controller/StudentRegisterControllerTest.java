package com.nhnacademy.controller;

import com.nhnacademy.domain.Student;
import com.nhnacademy.exception.StudentAlreadyExistsException;
import com.nhnacademy.repository.StudentRepository;
import com.nhnacademy.validator.StudentRegisterRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentRegisterController.class)
@Import(StudentRegisterRequestValidator.class)
class StudentRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void setUp() {
        // 테스트에 사용할 학생 객체 생성
        student = new Student("sslynn22", "seoin", "qwer1234", "sslynn22@naver.com", 100, "abc");
    }

    @Test
    void testStudentRegisterForm() throws Exception {
        mockMvc.perform(get("/register"));
    }

    @Test
    void registerStudentSuccessfully() throws Exception {
        when(studentRepository.register(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/register")
                        .param("id", "sslynn22")
                        .param("name", "seoin")
                        .param("password", "qwer1234")
                        .param("email", "sslynn22@naver.com")
                        .param("score", "100")
                        .param("address", "abc"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}
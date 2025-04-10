package com.nhnacademy.controller;

import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LogoutController.class)
class LogoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void logoutWithoutCookie() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void logout() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sslynnww", "testId");

        Cookie cookie = new Cookie("JSESSIONID", session.getId());

        mockMvc.perform(get("/logout")
                        .session(session)
                        .cookie(cookie))
                .andExpect(redirectedUrl("/login"))
                .andExpect(cookie().doesNotExist("JSESSIONID"));
    }
}
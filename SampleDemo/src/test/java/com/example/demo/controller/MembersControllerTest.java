package com.example.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class MembersControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    @DisplayName("method: Get")
    public void callGetTest() throws Exception {
        this.mvc.perform(get("/members"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}
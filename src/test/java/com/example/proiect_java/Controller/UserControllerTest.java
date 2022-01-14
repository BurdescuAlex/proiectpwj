package com.example.proiect_java.Controller;

import com.example.proiect_java.Repository.UserRepository;
import com.example.proiect_java.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@WebMvcTest(controllers = UserController.class)
@EnableTransactionManagement
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;


    @Test
    void register() throws Exception {
        String endpoint = "/user/register";
        mockMvc.perform(MockMvcRequestBuilders
                .post(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .param("name","Alexx")
                .param("password","parola")
                .param("email","alex15@email.com"))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    void login() {
    }

    @Test
    void changePassword() {
    }

    @Test
    void addMoney() {
    }

    @Test
    void substractMoney() {
    }

    @Test
    void seeMoney() {
    }
}
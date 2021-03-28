package com.example.dorywcza.controller;

import com.example.dorywcza.model.Address;
import com.example.dorywcza.model.user.User;
import com.example.dorywcza.model.user.UserProfile;
import com.example.dorywcza.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DirtiesContext
    void given3UsersInDatabase_whenGetAll_shouldReturn3Users() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/users"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseAsString = mvcResult.getResponse().getContentAsString();
        List<User> returnedUserList = objectMapper.readValue(responseAsString, new TypeReference<>() {
        });

        int expectedSize = 3;
        List<String> expectedEmails = new ArrayList<>(List.of("mark@gmail.com", "kazik@gmail.com","mati@gmail.com" )) ;
        List<String> expectedStreets = new ArrayList<>(List.of("SunnyStreet", "StormyStreet", "RainyStreet"));

        assertAll(
            () -> assertEquals(expectedSize,returnedUserList.size()),
            () -> assertEquals(expectedEmails, returnedUserList.stream().map(User::getEmail).collect(Collectors.toList())),
                () -> assertEquals(expectedStreets, returnedUserList.stream()
                        .map(User::getUserProfile)
                        .map(UserProfile::getAddress)
                        .map(Address::getStreet)
                        .collect(Collectors.toList())
                )
            );
    }

    @Test
    void getUser() {
    }
}
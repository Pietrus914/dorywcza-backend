package com.example.dorywcza.controller;

import com.example.dorywcza.model.user.User;
import com.example.dorywcza.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerMockBeanTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;



//    @Before
//    public void setUp(){
//        Mockito.when(userService.findAll())
//                .thenReturn(new ArrayList<>(List.of(
//                        new User("test1@gmail.com","password" ),
//                        new User("test2@gmail.com","password2"),
//                        new User("test3@gmail.com","password3")
//                )));
//
//    }

    @Test
    void given3UsersInDatabase_whenGetAll_shouldReturnListOf3Users() throws Exception {

        List<User> expectedList = new ArrayList<>(List.of(
                        new User("test1@gmail.com","password" ),
                        new User("test2@gmail.com","password2"),
                        new User("test3@gmail.com","password3")
                ));


        given(userService.findAll()).willReturn(expectedList);

        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/users"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseAsString = mvcResult.getResponse().getContentAsString();
        List<User> returnedUserList = objectMapper.readValue(responseAsString, new TypeReference<>() {
        });

        int expectedSize = 3;
        List<String> expectedEmails = new ArrayList<>(List.of("test1@gmail.com", "test2@gmail.com","test3@gmail.com" )) ;

        assertAll(
                () -> assertEquals(expectedSize,returnedUserList.size()),
                ()-> assertEquals(expectedEmails, returnedUserList.stream().map(User::getEmail).collect(Collectors.toList())),
                () -> assertEquals(expectedList, returnedUserList ));

    }
}
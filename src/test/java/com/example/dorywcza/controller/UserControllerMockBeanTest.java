package com.example.dorywcza.controller;

import com.example.dorywcza.model.user.DTO.UserPublicDTO;
import com.example.dorywcza.model.user.DTO.UserUpdateDTO;
import com.example.dorywcza.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
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

    private String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
    private HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
    private CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;


    private List<UserPublicDTO> expectedList = new ArrayList<>(List.of(
            new UserPublicDTO("test1@gmail.com" ),
            new UserPublicDTO("test2@gmail.com"),
            new UserPublicDTO("test3@gmail.com")
    ));

    @Test
    void given3UsersInDatabase_whenGetAll_shouldReturnListOf3Users() throws Exception {

        given(userService.findAll()).willReturn(expectedList);

        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/users"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseAsString = mvcResult.getResponse().getContentAsString();
        List<UserPublicDTO> returnedUserList = objectMapper.readValue(responseAsString, new TypeReference<>() {
        });

        int expectedSize = 3;
        List<String> expectedEmails = new ArrayList<>(List.of("test1@gmail.com", "test2@gmail.com","test3@gmail.com" )) ;

        assertAll(
                () -> assertEquals(expectedSize,returnedUserList.size()),
                ()-> assertEquals(expectedEmails, returnedUserList.stream().map(dto -> dto.getEmail()).collect(Collectors.toList())),
                () -> assertEquals(expectedList, returnedUserList ));

    }

    @Test
    void givenNewUserWithoutProfile_whenAdding_ShouldReturnExpectedUser() throws Exception {

        UserUpdateDTO newUserDTO = new UserUpdateDTO("emailTest@gmail.com");
        UserUpdateDTO expectedUserDTO = new UserUpdateDTO("fromService");
        String newUserInJson = objectMapper.writeValueAsString(newUserDTO);

        given(userService.addUser(newUserDTO)).willReturn(expectedUserDTO);

        MvcResult mvcResult;
        mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUserInJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        UserUpdateDTO returnedUserDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserUpdateDTO.class);

        assertAll(
                () -> assertEquals(expectedUserDTO, returnedUserDTO)
        );


    }
}
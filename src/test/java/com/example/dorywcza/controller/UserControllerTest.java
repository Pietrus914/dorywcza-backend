package com.example.dorywcza.controller;

import com.example.dorywcza.model.user.UserDTO;
import com.example.dorywcza.model.user.UserProfileDTO;
import com.example.dorywcza.util.Address;
import com.example.dorywcza.model.user.User;
import com.example.dorywcza.model.user.UserProfile;
import com.example.dorywcza.service.UserService;
import com.example.dorywcza.util.AddressDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
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

    private String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
    private HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
    private CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());


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
        List<UserDTO> returnedUserList = objectMapper.readValue(responseAsString, new TypeReference<>() {
        });

        int expectedSize = 3;
        List<String> expectedEmails = new ArrayList<>(List.of("mark@gmail.com", "kazik@gmail.com","mati@gmail.com" )) ;
        List<String> expectedStreets = new ArrayList<>(List.of("SunnyStreet", "StormyStreet", "RainyStreet"));

        assertAll(
            () -> assertEquals(expectedSize,returnedUserList.size()),
            () -> assertEquals(expectedEmails, returnedUserList.stream().map(UserDTO::getEmail).collect(Collectors.toList())),
                () -> assertEquals(expectedStreets, returnedUserList.stream()
                        .map(UserDTO::getUserProfileDTO)
                        .map(UserProfileDTO::getAddressDTO)
                        .map(AddressDTO::getStreet)
                        .collect(Collectors.toList())
                )
            );
    }

    @Test
    @DirtiesContext
    void givenNewUserWithoutProfile_whenAdding_ShouldReturnExpectedUser() throws Exception {

        UserDTO newUserDTO = new UserDTO("emailTest@gmail.com");
        String newUserDTOInJson = objectMapper.writeValueAsString(newUserDTO);

        MvcResult mvcResult;
        mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                        .param(csrfToken.getParameterName(), csrfToken.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUserDTOInJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        UserDTO returnedUserDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserDTO.class);

        assertAll(
                () -> assertEquals(newUserDTO, returnedUserDTO)
        );


    }

    @Test
    @DirtiesContext
    void givenUserWithoutProfile_whenUpdateUser_ShouldReturnUpdatedUser() throws Exception {

        UserDTO userDTOToUpdate = userService.findById(1L).get();
        userDTOToUpdate.getUserProfileDTO().setUser_name("newNick");
        String userDTOToUpdateInJson = objectMapper.writeValueAsString(userDTOToUpdate);

        MvcResult mvcResult;
        mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.put("/user/1")
                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                        .param(csrfToken.getParameterName(), csrfToken.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDTOToUpdateInJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        UserDTO returnedUserDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserDTO.class);

        assertAll(
                () -> assertEquals(userDTOToUpdate,
                        returnedUserDTO)
        );


    }
}
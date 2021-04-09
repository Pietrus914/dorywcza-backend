package com.example.dorywcza.controller;

import com.example.dorywcza.model.user.DTO.UserPublicDTO;
import com.example.dorywcza.model.user.DTO.UserUpdateDTO;
import com.example.dorywcza.service.UserService;
import com.example.dorywcza.util.ImageDTO;
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

import static org.junit.jupiter.api.Assertions.*;
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
        List<UserPublicDTO> returnedUserList = objectMapper.readValue(responseAsString, new TypeReference<>() {
        });

        int expectedSize = 3;
        List<String> expectedEmails = new ArrayList<>(List.of("mark@gmail.com", "kazik@gmail.com","mati@gmail.com" )) ;
        List<String> expectedStreets = new ArrayList<>(List.of("SunnyStreet", "StormyStreet", "RainyStreet"));
        List<ImageDTO> expectedExperienceImages = null;

        assertAll(
            () -> assertEquals(expectedSize,returnedUserList.size()),
            () -> assertEquals(expectedEmails, returnedUserList.stream().map(UserPublicDTO::getEmail).collect(Collectors.toList())),
            () -> assertEquals(expectedStreets, returnedUserList.stream()
                    .map(UserPublicDTO::getStreet)
                    .collect(Collectors.toList()))
//            () -> assertEquals(expectedExperienceImages,returnedUserList.stream()
//                        .map(UserPublicDTO::getPictures)
//                        .flatMap(List::stream)
////                        .map(ImageDTO::getType)
//                        .collect(Collectors.toList()))
            );
    }

    @Test
    @DirtiesContext
    void givenNewUserWithoutProfile_whenAdding_ShouldReturnExpectedUser() throws Exception {

        UserUpdateDTO newUserDTO = new UserUpdateDTO("emailTest@gmail.com");
        newUserDTO.setEmail("emailTest@gmail.com");

        String newUserDTOInJson = objectMapper.writeValueAsString(newUserDTO);

        MvcResult mvcResult;
        mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                        .param(csrfToken.getParameterName(), csrfToken.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUserDTOInJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        newUserDTO.setOverallRating(0);

        UserUpdateDTO returnedUserDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserUpdateDTO.class);

        assertAll(
                () -> assertEquals(newUserDTO, returnedUserDTO)
        );
    }

    @Test
    @DirtiesContext
    void givenUserWithoutProfile_whenUpdateUser_ShouldReturnUpdatedUser() throws Exception {

        UserUpdateDTO userDTOToUpdate = userService.findUpdateDTOById(1L).get();
        userDTOToUpdate.setUser_name("newNick");
        userDTOToUpdate.setExperienceDescription("strong man");
        String userDTOToUpdateInJson = objectMapper.writeValueAsString(userDTOToUpdate);

        MvcResult mvcResult;
        mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.put("/users/1")
                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                        .param(csrfToken.getParameterName(), csrfToken.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDTOToUpdateInJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        UserUpdateDTO returnedUserDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserUpdateDTO.class);

        assertAll(
                () -> assertEquals(userDTOToUpdate,
                        returnedUserDTO)
        );


    }

    @Test
    @DirtiesContext
    void givenUserDTOWithoutProfile_whenDeleteUser_ShouldReturnUserDTOWithDeletedStatus() throws Exception {

        UserUpdateDTO userDTOToUpdate = userService.findUpdateDTOById(1L).get();
        String userDTOToUpdateInJson = objectMapper.writeValueAsString(userDTOToUpdate);

        MvcResult mvcResult;
        mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/users/1")
                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                        .param(csrfToken.getParameterName(), csrfToken.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDTOToUpdateInJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        UserUpdateDTO returnedUserDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserUpdateDTO.class);

        assertAll(
                () -> assertEquals(userDTOToUpdate,
                        returnedUserDTO),
                () -> assertThrows(RuntimeException.class, () -> {
                    userService.findUserById(1L);})
        );
    }

}
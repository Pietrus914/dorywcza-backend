package com.example.dorywcza.controller;

public class ImageControllerTest {

    //    @Test
//    @DirtiesContext
//    void givenUserWithAvatar_whenAddUser_ShouldReturnBytesArray() throws Exception {
//        String fileName = "./images/birds_rainbow-lorakeets.png";
//        File avatar = new File(fileName);
//        UserDTO userDTOToAdd = userService.findById(1L).get();
//        userDTOToAdd.setId(null);
//        userDTOToAdd.setUser_name("NickWithAvatar");
//        userDTOToAdd.setAvatar(new ImageDTO("birds_rainbow-lorakeets.png", "image/png", "/images/1", ));
//        String userDTOToUpdateInJson = objectMapper.writeValueAsString(userDTOToAdd);
//
//        MvcResult mvcResult;
//        mvcResult = this.mockMvc.perform(
//                MockMvcRequestBuilders.put("/user/1")
//                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
//                        .param(csrfToken.getParameterName(), csrfToken.getToken())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userDTOToUpdateInJson))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        UserDTO returnedUserDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserDTO.class);
//
//        assertAll(
//                () -> assertEquals(avatar,
//                        ImageToByteArrayConverter.convertByteArrayToFile(returnedUserDTO.getAvatar(), fileName))
//        );
//
//
//    }
}

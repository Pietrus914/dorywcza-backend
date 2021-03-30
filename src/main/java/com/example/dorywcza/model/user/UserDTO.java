package com.example.dorywcza.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String phone_number;
    private int overallRating;
    private UserProfileDTO userProfileDTO;


    public UserDTO(Long id,
                   String email,
                   String phone_number,
                   int overallRating,
                   UserProfileDTO userProfileDTO) {
        this.id = id;
        this.email = email;
        this.phone_number = phone_number;
        this.overallRating = overallRating;
        this.userProfileDTO = userProfileDTO;
    }

    public UserDTO(String email) {
        this.email = email;
    }
}

package com.example.dorywcza.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String phone_number;
    private int overallRating;
    private UserProfileDTO userProfileDTO;


    public UserDTO(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.phone_number = user.getPhone_number();
        this.overallRating = user.getOverallRating();
        if (user.getUserProfile() != null){
            this.userProfileDTO = new UserProfileDTO(user.getUserProfile());
        }
    }

    public UserDTO(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return overallRating == userDTO.overallRating && Objects.equals(email, userDTO.email) && Objects.equals(phone_number, userDTO.phone_number) && Objects.equals(userProfileDTO, userDTO.userProfileDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone_number, overallRating, userProfileDTO);
    }
}

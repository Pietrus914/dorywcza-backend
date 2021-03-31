package com.example.dorywcza.model.user;

import com.example.dorywcza.util.AddressDTO;
import com.example.dorywcza.util.ExperienceDTO;
import com.example.dorywcza.util.ImageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProfileDTO {
    private Long id;
    private String first_name;
    private String last_name;
    private String user_name;
    private String description;
    private AddressDTO addressDTO;
    private ExperienceDTO experienceDTO;
    private ImageDTO avatar;


    public UserProfileDTO(UserProfile userProfile) {
        this.id = userProfile.getId();
        this.first_name = userProfile.getFirst_name();
        this.last_name = userProfile.getLast_name();
        this.user_name = userProfile.getUser_name();
        this.description = userProfile.getDescription();
        if (userProfile.getAddress() != null){
            this.addressDTO = new AddressDTO(userProfile.getAddress());
        }
        if (userProfile.getExperience() != null) {
            this.experienceDTO = new ExperienceDTO(userProfile.getExperience());
        }
        if (userProfile.getAvatar() != null) {
            this.avatar = new ImageDTO(userProfile.getAvatar());
        }
    }
}

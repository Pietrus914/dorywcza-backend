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



//    public UserProfileDTO(Long id, UserDTO userDTO, String first_name,
//                          String last_name, String user_name, String street,
//                          String description, String experienceDescription,
//                          File avatar, List<File> experienceImages) {
//        this.id = id;
//        this.userDTO = userDTO;
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.user_name = user_name;
//        this.street = street;
//        this.description = description;
//        this.experienceDescription = experienceDescription;
//        this.avatar = avatar;
//        this.experienceImages = experienceImages;
//    }

    public UserProfileDTO(UserProfile userProfile) {
        this.id = userProfile.getId();
        this.first_name = userProfile.getFirst_name();
        this.last_name = userProfile.getLast_name();
        this.user_name = userProfile.getUser_name();
        this.description = userProfile.getDescription();
        this.addressDTO = new AddressDTO(userProfile.getAddress());
        this.experienceDTO = new ExperienceDTO(userProfile.getExperience());
        this.avatar = new ImageDTO(userProfile.getAvatar());
        ;
    }
}

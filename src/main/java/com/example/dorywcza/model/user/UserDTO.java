package com.example.dorywcza.model.user;

import com.example.dorywcza.util.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String phone_number;
    private int overallRating;
    private String first_name;
    private String last_name;
    private String user_name;
    private String description;
    private String street;
    private String experienceDescription;
    private List<File> pictures;
    private File avatar;



    public UserDTO(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.phone_number = user.getPhone_number();
        this.overallRating = user.getOverallRating();
        if (hasProfile(user)){
            UserProfile userProfile = user.getUserProfile();
            this.first_name = userProfile.getFirst_name();
            this.last_name = userProfile.getLast_name();
            this.user_name = userProfile.getUser_name();
            this.description = userProfile.getDescription();

            if (userProfile.getAddress() != null){
                this.street = userProfile.getAddress().getStreet();
            }

            if (userProfile.getExperience() != null){
                this.experienceDescription = userProfile.getExperience().getDescription();
                if (userProfile.getExperience().getImageBox() != null && userProfile.getExperience().getImageBox().getImages() != null){
                    this.pictures = userProfile.getExperience().getImageBox().getImages()
                            .stream().map(Image::getImage).collect(Collectors.toList());
                }
            }

            this.avatar = userProfile.getAvatar().getImage();
        }
    }

    private boolean hasProfile(User user) {
        return user.getUserProfile() != null;
    }

    public UserDTO(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return overallRating == userDTO.overallRating && Objects.equals(email, userDTO.email) && Objects.equals(phone_number, userDTO.phone_number) && Objects.equals(first_name, userDTO.first_name) && Objects.equals(last_name, userDTO.last_name) && Objects.equals(user_name, userDTO.user_name) && Objects.equals(description, userDTO.description) && Objects.equals(street, userDTO.street) && Objects.equals(experienceDescription, userDTO.experienceDescription) && Objects.equals(pictures, userDTO.pictures) && Objects.equals(avatar, userDTO.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone_number, overallRating, first_name, last_name, user_name, description, street, experienceDescription, pictures, avatar);
    }
}

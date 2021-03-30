package com.example.dorywcza.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

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
    private String street;
    private String description;
    private String experienceDescription;
    private File avatar;
    private List<File> experienceImages;


    public UserDTO(Long id, String email, String phone_number,
                   int overallRating, String first_name,
                   String last_name, String user_name,
                   String street, String description,
                   String experienceDescription, File avatar,
                   List<File> experienceImages) {
        this.id = id;
        this.email = email;
        this.phone_number = phone_number;
        this.overallRating = overallRating;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_name = user_name;
        this.street = street;
        this.description = description;
        this.experienceDescription = experienceDescription;
        this.avatar = avatar;
        this.experienceImages = experienceImages;
    }


}

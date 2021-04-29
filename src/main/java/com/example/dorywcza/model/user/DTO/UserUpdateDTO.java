package com.example.dorywcza.model.user.DTO;

import com.example.dorywcza.model.user.Experience;
import com.example.dorywcza.model.user.User;
import com.example.dorywcza.model.user.UserProfile;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@ApiModel(description = "DTO that extends UserGeneralDTO, contains user details without urls to images")
@Data
@NoArgsConstructor
public class UserUpdateDTO extends UserGeneralDTO {

    private String experienceDescription;

    public UserUpdateDTO(User user){
        super(user);
        if (user.hasProfile()){
            loadExperienceDescription(user.getUserProfile());
        }
    }

    public UserUpdateDTO(String email){
        super(email);
    }

    private void loadExperienceDescription(UserProfile userProfile) {
        if (userProfile.hasExperience()) {
            Experience experience = userProfile.getExperience();
            this.experienceDescription = experience.getDescription();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserUpdateDTO that = (UserUpdateDTO) o;
        return Objects.equals(experienceDescription, that.experienceDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experienceDescription);
    }
}

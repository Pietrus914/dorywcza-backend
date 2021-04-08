package com.example.dorywcza.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class UserUpdateDTO extends UserGeneralDTO {

    private String experienceDescription;

    public UserUpdateDTO(User user){
        super(user);
        if (user.hasProfile()){
            loadExperience(user.getUserProfile());
        }
    }

    private void loadExperience(UserProfile userProfile) {
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

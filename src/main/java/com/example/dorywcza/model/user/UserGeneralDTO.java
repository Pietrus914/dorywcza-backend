package com.example.dorywcza.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Data
@NoArgsConstructor
@MappedSuperclass
public class UserGeneralDTO {

    private Long id;
    private String email;
    private String phone_number;
    private int overallRating;
    private String first_name;
    private String last_name;
    private String user_name;
    private String description;
    private String street;


    public UserGeneralDTO(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.phone_number = user.getPhone_number();
        this.overallRating = user.getOverallRating();
        if (user.hasProfile()){
            loadUserProfile(user.getUserProfile());
        }
    }
    private void loadUserProfile(UserProfile userProfile) {
        this.first_name = userProfile.getFirst_name();
        this.last_name = userProfile.getLast_name();
        this.user_name = userProfile.getUser_name();
        this.description = userProfile.getDescription();

        loadAddress(userProfile);
    }

    private void loadAddress(UserProfile userProfile) {
        if (userProfile.hasAddress()){
            this.street = userProfile.getAddress().getStreet();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGeneralDTO that = (UserGeneralDTO) o;
        return overallRating == that.overallRating && Objects.equals(email, that.email) && Objects.equals(phone_number, that.phone_number) && Objects.equals(first_name, that.first_name) && Objects.equals(last_name, that.last_name) && Objects.equals(user_name, that.user_name) && Objects.equals(description, that.description) && Objects.equals(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone_number, overallRating, first_name, last_name, user_name, description, street);
    }
}

package com.example.dorywcza.model.user.DTO;

import com.example.dorywcza.model.user.User;
import com.example.dorywcza.model.user.UserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class UserGeneralDTO {

    private Long id;
    private String email;
    private String phoneNumber;
    private int overallRating;
    private String firstName;
    private String lastName;
    private String userName;
    private String description;
    private String street;

    public UserGeneralDTO(String email){
        this.email = email;
    }

    public UserGeneralDTO(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.overallRating = user.getOverallRating();
        if (user.hasProfile()){
            loadUserProfile(user.getUserProfile());
        }
    }
    private void loadUserProfile(UserProfile userProfile) {
        this.firstName = userProfile.getFirstName();
        this.lastName = userProfile.getLastName();
        this.userName = userProfile.getUserName();
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
        return overallRating == that.overallRating && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(userName, that.userName) && Objects.equals(description, that.description) && Objects.equals(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phoneNumber, overallRating, firstName, lastName, userName, description, street);
    }
}

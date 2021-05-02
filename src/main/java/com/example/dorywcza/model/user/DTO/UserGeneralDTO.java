package com.example.dorywcza.model.user.DTO;

import com.example.dorywcza.model.user.User;
import com.example.dorywcza.model.user.UserProfile;
import com.example.dorywcza.util.Address;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.util.Objects;

@ApiModel(description = "Abstract class with details of user")
@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class UserGeneralDTO extends UserDTO{
    @ApiModelProperty(required = true, value = "Identifier")
    private Long id;
    @ApiModelProperty(required = true, value = "Email of the user")
    private String email;
    @ApiModelProperty(value = "Phone number of the user")
    private String phoneNumber;
    @ApiModelProperty(value = "Rating of the user - gained when other users give 'vote-up' mark for him")
    private int overallRating;
    @ApiModelProperty(value = "First name of the user")
    private String firstName;
    @ApiModelProperty(value = "Surname of the user")
    private String lastName;
    @ApiModelProperty(value = "Name used to identify the user")
    private String userName;
    @ApiModelProperty(value = "Short text about the user that allows him to present himself to others users")
    private String description;
    @ApiModelProperty(value = "Address field: street, optional")
    private String street;
    @ApiModelProperty(value = "Address field: flat number, optional")
    private String flatNumber;
    @ApiModelProperty(value = "Address field: postcode, optional")
    private Integer zipCode;
    @ApiModelProperty(value = "Address field: city, optional")
    private String city;

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
            Address address = userProfile.getAddress();
            this.street = address.getStreet();
            this.flatNumber = address.getFlatNumber();
            this.zipCode = address.getZipCode();
            this.city = address.getCity();
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

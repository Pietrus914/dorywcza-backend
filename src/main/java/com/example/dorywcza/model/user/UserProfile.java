package com.example.dorywcza.model.user;


import com.example.dorywcza.util.Address;
import com.example.dorywcza.util.Image;
import com.example.dorywcza.util.ImageBox;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class UserProfile {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @PrimaryKeyJoinColumn(name = "user_id")
    private User user;

    private String firstName;
    private String lastName;
    private String userName;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private Experience experience;
    @OneToOne(cascade = CascadeType.ALL)
    private Image avatar;


    public UserProfile(User user){
        this.user = user;
        this.address = new Address();
        this.experience = new Experience();
        this.avatar = new Image();
    }

    public UserProfile(User user, String firstName, String lastName, String userName,
                       String description, String street, String experienceDescription,
                       List<Image> pictures, Image avatar){
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.description = description;
        this.address = new Address();
        address.setStreet(street);
        this.experience = new Experience();
        experience.setDescription(experienceDescription);
        experience.setImageBox(new ImageBox(pictures));
        this.avatar = avatar;
    }

    public boolean hasAddress(){
        return getAddress() != null;
    }

    public boolean hasExperience(){
        return getExperience() != null;
    }

    public boolean hasAvatar(){
        return getAvatar() !=null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(userName, that.userName) && Objects.equals(address, that.address) && Objects.equals(description, that.description) && Objects.equals(experience, that.experience) && Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, userName, address, description, experience, avatar);
    }
}

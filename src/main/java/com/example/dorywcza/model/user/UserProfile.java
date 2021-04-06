package com.example.dorywcza.model.user;


import com.example.dorywcza.util.Address;
import com.example.dorywcza.util.Image;
import com.example.dorywcza.util.ImageBox;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @PrimaryKeyJoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private String first_name;
    private String last_name;
    private String user_name;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private Experience experience;
    @OneToOne(cascade = CascadeType.ALL)
    private Image avatar;


    public UserProfile(User user, String first_name, String last_name, String user_name,
                       String description, String street, String experienceDescription,
                       List<byte[]> pictures, byte[] avatar){
        this.user = user;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_name = user_name;
        this.description = description;
        this.address = new Address();
        address.setStreet(street);
        this.experience = new Experience();
        experience.setDescription(experienceDescription);
        experience.setImageBox(new ImageBox(pictures));
        this.avatar = new Image(avatar);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(first_name, that.first_name) && Objects.equals(last_name, that.last_name) && Objects.equals(user_name, that.user_name) && Objects.equals(address, that.address) && Objects.equals(description, that.description) && Objects.equals(experience, that.experience) && Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, last_name, user_name, address, description, experience, avatar);
    }
}

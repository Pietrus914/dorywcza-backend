package com.example.dorywcza.model.user;


import com.example.dorywcza.model.Address;
import com.example.dorywcza.model.Image;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@NoArgsConstructor
@Data
@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(first_name, that.first_name) && Objects.equals(last_name, that.last_name) && Objects.equals(user_name, that.user_name) && Objects.equals(address, that.address) && Objects.equals(description, that.description) && Objects.equals(experience, that.experience) && Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, first_name, last_name, user_name, address, description, experience, avatar);
    }
}
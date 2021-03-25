package com.example.dorywcza.model.user;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.NotFound;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;
    @NotNull
    private String password;
    private String phone_number;
    private boolean verified;
    private int overallRating;

    @Transient
    UserProfile userProfile;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

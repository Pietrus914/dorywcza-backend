package com.example.dorywcza.model.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;

@SQLDelete(sql =
        "UPDATE user " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    UserProfile userProfile;

    @Column(name = "deleted")
    private boolean deleted;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.deleted = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return verified == user.verified && overallRating == user.overallRating && deleted == user.deleted && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(phone_number, user.phone_number) && Objects.equals(userProfile, user.userProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, phone_number, verified, overallRating, userProfile, deleted);
    }
}

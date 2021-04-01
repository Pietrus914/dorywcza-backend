package com.example.dorywcza.model.user;

import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.offer.Offer;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<JobOffer> jobOffers;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<ServiceOffer> serviceOffers;

    @Column(name = "deleted")
    private boolean deleted;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.deleted = false;
    }

}

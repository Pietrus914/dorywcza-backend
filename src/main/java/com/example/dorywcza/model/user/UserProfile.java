package com.example.dorywcza.model.user;


import com.example.dorywcza.model.Address;
import com.example.dorywcza.model.Image;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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




}

package com.example.dorywcza.model.user;


import com.example.dorywcza.model.Address;
import com.example.dorywcza.model.Image;
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
    private User user;

    private String first_name;
    private String last_name;
    private String user_name;
    @OneToOne
    private Address address;
    private String description;
    @OneToOne
    private Experience experience;
    @OneToOne
    private Image avatar;




}

package com.example.dorywcza.model;

import com.example.dorywcza.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;
    private String review;
    @OneToOne
    @JsonBackReference
    private User reviewedByUser;
    @OneToOne
    @JsonBackReference
    private User user;

}

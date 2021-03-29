package com.example.dorywcza.model.user;

import com.example.dorywcza.model.ImageBox;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private ImageBox imageBox;


}

package com.example.dorywcza.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;

@NoArgsConstructor
@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private File image;
    @ManyToOne
    @JoinColumn(name = "image_box_id", nullable = false)
    @JsonBackReference
    private ImageBox imageBox;

}

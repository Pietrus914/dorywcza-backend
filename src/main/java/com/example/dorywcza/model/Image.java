package com.example.dorywcza.model;


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
//    @JoinTable(name = "image_image_box_id",
//            joinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "image_box_id", referencedColumnName = "id"))
    @JoinColumn(name = "image_box_id", nullable = false)
    private ImageBox box;

}

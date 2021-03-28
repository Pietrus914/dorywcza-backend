package com.example.dorywcza.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class ImageBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "imageBox", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Image> images;

    public ImageBox() {
        this.images = new ArrayList<>();
    }
}

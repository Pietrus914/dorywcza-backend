package com.example.dorywcza.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class ImageBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "box", cascade = CascadeType.REMOVE)
    private List<Image> images;

    public ImageBox() {
        this.images = new ArrayList<>();
    }
}

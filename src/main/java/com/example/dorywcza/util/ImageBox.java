package com.example.dorywcza.util;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Getter
@Setter
@Entity
public class ImageBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "imageBox", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Image> images;

    public ImageBox() {
        this.images = new ArrayList<>();
    }

    public ImageBox(List<File> pictures){
        this.images = pictures.stream().map(file -> new Image(file)).collect(Collectors.toList());
    }

    public ImageBox(ImageBoxDTO imageBoxDTO) {
        this.id = imageBoxDTO.getId();
        if (imageBoxDTO.getPictures().size() != 0){
        this.images = imageBoxDTO.getPictures().stream()
                .map(picture -> new Image(picture)).collect(Collectors.toList());}

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageBox imageBox = (ImageBox) o;
        return Objects.equals(images.size(), imageBox.images.size());
    }

    @Override
    public int hashCode() {
        return Objects.hash(images);
    }
}

package com.example.dorywcza.util;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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

    public ImageBox(List<byte[]> pictures){
        if (pictures != null){
            this.images = pictures.stream().map(Image::new).collect(Collectors.toList());
        }
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

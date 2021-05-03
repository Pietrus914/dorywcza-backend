package com.example.dorywcza.util;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Entity
public class ImageBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "imageBox", cascade = {CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Image> images;

    public ImageBox() {
        this.images = new ArrayList<>();
    }

    public ImageBox(List<Image> pictures){
        if (pictures != null){
            this.images = pictures;
        }
    }

    public boolean hasPicturesInImages(){
        return images != null;
    }

    public boolean isNotEmpty(){
        return images.size() != 0;
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

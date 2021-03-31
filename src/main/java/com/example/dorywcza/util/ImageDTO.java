package com.example.dorywcza.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@NoArgsConstructor
@Data
public class ImageDTO {

    private Long id;
    private File picture;

    public ImageDTO(Image image){
        this.id = image.getId();
        if (image.getImage() != null) {
            this.picture = image.getImage();
        }
    }
}

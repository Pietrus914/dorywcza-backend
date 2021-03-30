package com.example.dorywcza.util;

import lombok.Data;

import java.io.File;

@Data
public class ImageDTO {

    private Long id;
    private File picture;

    public ImageDTO(Image image){
        this.id = image.getId();
        this.picture = image.getImage();
    }
}

package com.example.dorywcza.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@NoArgsConstructor
@Data
public class ImageDTO {

//    private Long id;
    private String name;
    private String type;
    private String url;
    private long size;

    public ImageDTO(String name, String type, String url, long size) {
        this.name = name;
        this.type = type;
        this.url = url;
        this.size = size;
    }


    //    public ImageDTO(Image image){
//        this.id = image.getId();
//        if (image.getImage() != null) {
//            this.picture = image.getImage();
//        }
//    }
}

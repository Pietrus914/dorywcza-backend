package com.example.dorywcza.util;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "DTO that provides urls to image and details of it")
@NoArgsConstructor
@Data
public class ImageDTO {
    private static String pathString = "/resources/";

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

    public ImageDTO(Image image){
        this.name = image.getImageName();
        this.type = image.getType();
        this.url =  pathString + image.getId();
        this.size = image.getImage().length;
    }

}

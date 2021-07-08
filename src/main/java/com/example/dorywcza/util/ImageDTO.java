package com.example.dorywcza.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "DTO that provides urls to image and details of it")
@NoArgsConstructor
@Data
public class ImageDTO {
    @ApiModelProperty(value = "Common part of url for building an url to obtain the image")
    private static String pathString = "/resources/";
    @ApiModelProperty(required = true, value = "Identifier")
    private Long id;
    @ApiModelProperty(value = "Name of the file")
    private String name;
    @ApiModelProperty(value = "Mime type of the file")
    private String type;
    @ApiModelProperty(value = "Th url to obtain the image")
    private String url;
    @ApiModelProperty(value = "Size of the image")
    private long size;

    public ImageDTO(String name, String type, String url, long size) {
        this.name = name;
        this.type = type;
        this.url = url;
        this.size = size;
    }

    public ImageDTO(Image image){
        this.id = image.getId();
        this.name = image.getImageName();
        this.type = image.getType();
        this.url =  pathString + image.getId();
        this.size = image.getImage().length;
    }

}

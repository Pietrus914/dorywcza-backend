package com.example.dorywcza.util;

import lombok.Data;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ImageBoxDTO {

    private Long id;
    private List<ImageDTO> pictures;

    public ImageBoxDTO(ImageBox imageBox){
        this.id = imageBox.getId();
        this.pictures = imageBox.getImages().stream()
                .map(image -> new ImageDTO(image))
                .collect(Collectors.toList());
    }
}

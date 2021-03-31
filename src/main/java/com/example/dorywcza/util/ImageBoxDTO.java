package com.example.dorywcza.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class ImageBoxDTO {

    private Long id;
    private List<ImageDTO> pictures;

    public ImageBoxDTO(ImageBox imageBox){
        this.id = imageBox.getId();
        if (imageBox.getImages() != null){
        this.pictures = imageBox.getImages().stream()
                .map(image -> new ImageDTO(image))
                .collect(Collectors.toList());}
    }
}

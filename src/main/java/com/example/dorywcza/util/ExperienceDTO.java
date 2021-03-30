package com.example.dorywcza.util;

import com.example.dorywcza.model.user.Experience;
import lombok.Data;

@Data
public class ExperienceDTO {

    private Long id;
    private String description;
    private ImageBoxDTO imageBoxDTO;

    public ExperienceDTO(Experience experience) {
        this.id = experience.getId();
        this.description = experience.getDescription();
        this.imageBoxDTO = new ImageBoxDTO(experience.getImageBox());
    }
}

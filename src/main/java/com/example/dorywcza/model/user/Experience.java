package com.example.dorywcza.model.user;

import com.example.dorywcza.util.ExperienceDTO;
import com.example.dorywcza.util.ImageBox;
import com.example.dorywcza.util.ImageBoxDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private ImageBox imageBox;

    public Experience(ExperienceDTO experienceDTO) {
        this.id = experienceDTO.getId();
        this.description = experienceDTO.getDescription();
        this.imageBox = new ImageBox(experienceDTO.getImageBoxDTO());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(description, that.description) && Objects.equals(imageBox, that.imageBox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, imageBox);
    }



}

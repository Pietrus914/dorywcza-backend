package com.example.dorywcza.util;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.util.Objects;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] image;
    @ManyToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "image_box_id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ImageBox imageBox;

    private String imageName;
    private String type;


    public Image(byte[] file){
        if (file != null){
            this.image = file;
        }
    }

    public Image(MultipartFile file, ImageBox imageBox) throws IOException {
        if (file != null){
            this.image = file.getBytes();
            this.type = file.getContentType();
            this.imageName = getFileName(file);
            this.imageBox = imageBox;
        }
    }

    private String getFileName(MultipartFile file) {
        return StringUtils.cleanPath(file.getOriginalFilename());
    }

    public Image(byte[] file,String type, String imageName, ImageBox imageBox){
        if (file != null){
            this.image = file;
            this.type = type;
            this.imageName = imageName;
            this.imageBox = imageBox;
        }
    }

    public boolean hasFile(){
        return image != null;
    }

    public ImageDTO convert(Image image){
        return new ImageDTO(image.getImageName(), image.getType(), "/images/"+ image.getId(), image.getImage().length );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image1 = (Image) o;
        return Objects.equals(image, image1.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image);
    }
}

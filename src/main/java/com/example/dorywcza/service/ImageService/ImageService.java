package com.example.dorywcza.service.ImageService;

import com.example.dorywcza.model.user.User;
import com.example.dorywcza.repository.ImageRepository.ImageRepository;
import com.example.dorywcza.repository.UserRepository;
import com.example.dorywcza.util.Image;
import com.example.dorywcza.util.ImageBox;
import com.example.dorywcza.util.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;

    public Image store(MultipartFile file, Long userId, Boolean isAvatar) throws IOException {
        if (isAvatar){
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            Image image = new Image(file.getBytes(), file.getContentType(), fileName,null);
            User user = userRepository.findById(userId).get();
            image.setId(user.getUserProfile().getAvatar().getId());
            return imageRepository.save(image);
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ImageBox imageBox = userRepository.findById(userId).get().getUserProfile().getExperience().getImageBox();
        Image image = new Image(file.getBytes(), file.getContentType(), fileName, imageBox);
        return imageRepository.save(image);
    }

    public ImageDTO findImage(Long id){
        if (!imageRepository.existsById(id)) throw new RuntimeException("User Not Found");
        Image image = imageRepository.findById(id).get();
        return new ImageDTO(image.getImageName(),image.getType(),"/images/"+ image.getId(), image.getImage().length);
    }

    public Image findRealImage(Long id){
        if (!imageRepository.existsById(id)) throw new RuntimeException("User Not Found");
        return imageRepository.findById(id).get();
    }


    public List<ImageDTO> getAllImages(){
        List<Image> images =  imageRepository.findAll();
        List<ImageDTO> imageDTOList = images.stream()
                .filter(image -> (image != null && image.getImage() != null))
                .map(image -> {
            String fileUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path("" + image.getId())
                    .toUriString();
            return new ImageDTO(image.getImageName(),image.getType(),fileUri, image.getImage().length);
        }).collect(Collectors.toList());

        return imageDTOList;
    }

    public List<Image> findRealImagesByIds(List<Long> imageIds) {
        return imageIds.stream()
                .filter(i -> imageRepository.existsById(i))
                .map(i -> imageRepository.findById(i).get()).collect(Collectors.toList());
    }
}

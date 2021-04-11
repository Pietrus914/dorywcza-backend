package com.example.dorywcza.service.ImageService;

import com.example.dorywcza.model.user.User;
import com.example.dorywcza.model.user.UserProfile;
import com.example.dorywcza.repository.ImageRepository.ImageRepository;
import com.example.dorywcza.repository.UserRepository;
import com.example.dorywcza.service.UserService;
import com.example.dorywcza.util.Image;
import com.example.dorywcza.util.ImageBox;
import com.example.dorywcza.util.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Autowired
    private UserService userService;

    public ImageService(ImageRepository imageRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Image store(MultipartFile file, Long userId, Boolean isAvatar) throws IOException {
        User user = userRepository.findById(userId).orElseThrow();
        if (!user.hasProfile()){
            user.setUserProfile(new UserProfile(user));
            userRepository.save(user);
        }
        if (isAvatar){

            Image image = new Image(file,null);
            image.setId(user.getUserProfile().getAvatar().getId());
            return imageRepository.save(image);
        }
        ImageBox imageBox = user.getUserProfile().getExperience().getImageBox();
        Image image = new Image(file, imageBox);
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

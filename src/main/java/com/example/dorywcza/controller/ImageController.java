package com.example.dorywcza.controller;

import com.example.dorywcza.service.ImageService.ImageService;
import com.example.dorywcza.util.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @ResponseStatus(HttpStatus.OK)   //in case positive scenario it will send 200, if not: 500
    @PostMapping("/upload")
    public void uploadImage(@RequestParam("image") MultipartFile image,
                              @RequestParam("userId") Long userId,
                              @RequestParam("avatar") Boolean isAvatar) throws IOException {

            imageService.store(image, userId, isAvatar);
    }

    @GetMapping("/images/{id}")
    public ImageDTO getImage(@PathVariable Long id){
        return imageService.findImage(id);
    }

    @GetMapping("/images")
    public List<ImageDTO> getAllImages(){
        return imageService.getAllImages();
    }

    @GetMapping(value="/resources/{id}", produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_PNG_VALUE})
        public @ResponseBody byte[] getRealImage(@PathVariable Long id){
            return imageService.findRealImage(id);
        }

}

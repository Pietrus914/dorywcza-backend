package com.example.dorywcza.controller;

import com.example.dorywcza.exceptions.ErrorDTO;
import com.example.dorywcza.service.ImageService;
import com.example.dorywcza.util.ImageDTO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @ApiOperation("Upload an imageDTO. Necessary parameters: [MultipartFile image , Long userId , Boolean avatar]")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Image uploaded successfully", response = Void.class),
            @ApiResponse(code = 400, message = "Not valid parameter", response = ErrorDTO.class)})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User's id", required = true, dataType = "long", paramType = "form"),
            @ApiImplicitParam(name = "avatar", value = "true for avatar, false for other images", required = true, dataType = "Boolean", paramType = "form"),
            @ApiImplicitParam(name = "image", value = "Image", required = true, dataType = "_file", paramType = "form")
    })
    @ResponseStatus(HttpStatus.OK)   //in case of positive scenario it will send 200, if not: 500
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void uploadImage(@RequestPart("image") MultipartFile image,
                              @RequestParam("userId") Long userId,
                              @RequestParam("avatar") Boolean isAvatar) throws IOException {

            imageService.store(image, userId, isAvatar);
    }

    @ApiOperation("Gets na imageDT according to passed image id")
    @ApiResponse(code = 200, message = "Gets na imageDTO", response = ImageDTO.class)
    @GetMapping("/images/{id}")
    public ImageDTO getImage(@PathVariable Long id){
        return imageService.findImage(id);
    }

    @ApiOperation("Gets a list of images' DTOs")
    @ApiResponse(code = 200, message = "Gets a list of images' DTOs", response = ImageDTO.class, responseContainer = "List")
    @GetMapping("/images")
    public List<ImageDTO> getAllImages(){
        return imageService.getAllImages();
    }

    @ApiOperation("Retrives an image as byte array as MediaType: jpg, gif, png")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "An image successfully retrieved", response = byte.class, responseContainer = "array")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Image's id", required = true, dataType = "long", paramType = "path")
    })
    @GetMapping(value="/resources/{id}", produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_PNG_VALUE})
        public @ResponseBody byte[] getRealImage(@PathVariable Long id){
            return imageService.findRealImage(id);
        }

}

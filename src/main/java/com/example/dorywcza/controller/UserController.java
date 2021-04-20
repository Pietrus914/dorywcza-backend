package com.example.dorywcza.controller;

import com.example.dorywcza.exceptions.ParameterNotValid;
import com.example.dorywcza.model.user.DTO.UserDTO;
import com.example.dorywcza.model.user.DTO.UserPublicDTO;
import com.example.dorywcza.model.user.DTO.UserUpdateDTO;
import com.example.dorywcza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserPublicDTO> getUsers(){
        return userService.findAll();
    }

    @Validated
    @GetMapping("/users/{id}")
    public Optional<? extends UserDTO> getUser(@PathVariable Long id, @RequestParam String type) {
        List<String> params = new ArrayList<>(List.of("public", "update", "simplified"));
        if (!params.contains(type)){
            throw new ParameterNotValid(HttpStatus.BAD_REQUEST, "Not valid parameter: accepted params: update, public, simplified");
        }
        switch (type){
            case "public":
                return  userService.findPublicDTOById(id);
            case "update":
                return userService.findUpdateDTOById(id);
            default:
                //when "simplified":
                return userService.findSimplifiedDTOById(id);
        }
    }

    @PostMapping("/users")
    public UserUpdateDTO addUser(@RequestBody UserUpdateDTO userDTO){
        return userService.addUser(userDTO);
    }

    @PutMapping("/users/{id}")
    public UserUpdateDTO updateUser(@RequestBody UserUpdateDTO userDTO, @PathVariable Long id){
        return userService.updateUser(userDTO, id);
    }

    @DeleteMapping("/users/{id}")
    public UserUpdateDTO deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}

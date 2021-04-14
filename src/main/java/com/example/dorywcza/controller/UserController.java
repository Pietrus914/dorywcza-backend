package com.example.dorywcza.controller;

import com.example.dorywcza.model.user.DTO.UserDTO;
import com.example.dorywcza.model.user.DTO.UserPublicDTO;
import com.example.dorywcza.model.user.DTO.UserUpdateDTO;
import com.example.dorywcza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserPublicDTO> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<? extends UserDTO> getUser(@PathVariable Long id, @RequestParam String type){
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

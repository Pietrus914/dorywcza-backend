package com.example.dorywcza.controller;

import com.example.dorywcza.model.user.UserPublicDTO;
import com.example.dorywcza.model.user.UserUpdateDTO;
import com.example.dorywcza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
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
    public Optional<UserPublicDTO> getUser(@PathVariable Long id){
        return userService.findPublicDTOById(id);
    }

    @GetMapping("/users-update/{id}")
    public Optional<UserUpdateDTO> getUserToUpdate(@PathVariable Long id, @RequestParam boolean toUpdate){
        return userService.findUpdateDTOById(id);
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
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}

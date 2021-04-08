package com.example.dorywcza.controller;

import com.example.dorywcza.model.user.UserDTO;
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
    public List<UserDTO> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<UserDTO> getUser(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping("/users")
    public UserDTO addUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
        return userService.updateUser(userDTO, id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}

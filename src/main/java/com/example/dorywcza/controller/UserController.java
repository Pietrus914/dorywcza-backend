package com.example.dorywcza.controller;

import com.example.dorywcza.model.user.User;
import com.example.dorywcza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping("/user")
    public void addUser(){

    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Long id){

    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}

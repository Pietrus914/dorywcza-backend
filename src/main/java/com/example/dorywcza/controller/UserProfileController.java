package com.example.dorywcza.controller;

import com.example.dorywcza.model.user.UserProfile;
import com.example.dorywcza.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/users-profiles")
    public List<UserProfile> findAll(){
        return userProfileService.findAll();
    }


}

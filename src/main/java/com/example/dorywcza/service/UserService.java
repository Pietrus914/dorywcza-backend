package com.example.dorywcza.service;

import com.example.dorywcza.model.user.User;

public interface UserService {
    void save(User user);

    User findByEmail(String email);
}

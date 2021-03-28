package com.example.dorywcza.service;

import com.example.dorywcza.model.user.User;
import com.example.dorywcza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}

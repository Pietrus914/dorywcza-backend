package com.example.dorywcza.service;

import com.example.dorywcza.model.Industry;
import com.example.dorywcza.repository.IndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndustryService {

    private IndustryRepository repository;

    @Autowired
    public IndustryService(IndustryRepository repository) {
        this.repository = repository;
    }

    public Optional<Industry> findByIdId(Long id){
        return repository.findById(id);
    }
}

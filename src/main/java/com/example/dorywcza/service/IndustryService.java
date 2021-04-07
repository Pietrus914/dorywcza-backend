package com.example.dorywcza.service;

import com.example.dorywcza.exceptions.RecordNotFound;
import com.example.dorywcza.model.offer.Industry;
import com.example.dorywcza.repository.IndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndustryService {

    private IndustryRepository repository;

    @Autowired
    public IndustryService(IndustryRepository repository) {
        this.repository = repository;
    }

    public Industry findById(Long id){
        Optional<Industry> industry = repository.findById(id);
        if (industry.isEmpty())
            throw new RecordNotFound(HttpStatus.UNPROCESSABLE_ENTITY, "No such Industry Id");
        return industry.get();
    }
}

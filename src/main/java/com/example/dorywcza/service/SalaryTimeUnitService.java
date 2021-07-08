package com.example.dorywcza.service;

import com.example.dorywcza.exceptions.RecordNotFound;
import com.example.dorywcza.model.offer.SalaryTimeUnit;
import com.example.dorywcza.repository.SalaryTimeUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalaryTimeUnitService {

    private SalaryTimeUnitRepository salaryTimeUnitRepository;

    @Autowired
    public SalaryTimeUnitService(SalaryTimeUnitRepository salaryTimeUnitRepository) {
        this.salaryTimeUnitRepository = salaryTimeUnitRepository;
    }

    public SalaryTimeUnit findSalaryTimeUnitById(Long id){
        Optional<SalaryTimeUnit> salaryTimeUnit = salaryTimeUnitRepository.findById(id);
        if (salaryTimeUnit.isEmpty())
            throw new RecordNotFound(HttpStatus.UNPROCESSABLE_ENTITY, "No such salary time unit");
        return salaryTimeUnit.get();
    }
}

package com.example.dorywcza.service;

import com.example.dorywcza.model.SalaryTimeUnit;
import com.example.dorywcza.repository.SalaryTimeUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryTimeUnitService {

    private SalaryTimeUnitRepository salaryTimeUnitRepository;

    @Autowired
    public SalaryTimeUnitService(SalaryTimeUnitRepository salaryTimeUnitRepository) {
        this.salaryTimeUnitRepository = salaryTimeUnitRepository;
    }

    public SalaryTimeUnit findSalaryTimeUnitById(Long id){
        return salaryTimeUnitRepository.findById(id).get();
    }
}

package com.example.dorywcza.service;

import com.example.dorywcza.model.service_offer.ServiceJobSalary;
import com.example.dorywcza.repository.ServiceJobSalaryRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceJobSalaryService {
    private final ServiceJobSalaryRepository serviceJobSalaryRepository;

    public ServiceJobSalaryService(ServiceJobSalaryRepository serviceJobSalaryRepository) {
        this.serviceJobSalaryRepository = serviceJobSalaryRepository;
    }

    public void addServiceJobSalary(ServiceJobSalary serviceJobSalary) {
        serviceJobSalaryRepository.save(serviceJobSalary);
    }
}

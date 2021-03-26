package com.example.dorywcza.service;

import com.example.dorywcza.model.service_offer.ServiceLocation;
import com.example.dorywcza.repository.ServiceLocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceLocationService {
    private final ServiceLocationRepository serviceLocationRepository;

    public ServiceLocationService(ServiceLocationRepository serviceLocationRepository) {
        this.serviceLocationRepository = serviceLocationRepository;
    }

    public List<ServiceLocation> findAll() {
        return serviceLocationRepository.findAll();
    }

    public void addServiceLocation(ServiceLocation serviceLocation) {
        serviceLocationRepository.save(serviceLocation);
    }
}

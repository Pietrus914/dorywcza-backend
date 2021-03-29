package com.example.dorywcza.service;

import com.example.dorywcza.model.service_offer.ServiceDateRange;
import com.example.dorywcza.repository.ServiceDataRangeRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceDataRangeService {
    private final ServiceDataRangeRepository serviceDataRangeRepository;

    public ServiceDataRangeService(ServiceDataRangeRepository serviceDataRangeRepository) {
        this.serviceDataRangeRepository = serviceDataRangeRepository;
    }

    public void addServiceDataRange(ServiceDateRange serviceDateRange) {
        serviceDataRangeRepository.save(serviceDateRange);
    }
}

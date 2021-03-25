package com.example.dorywcza.service;

import com.example.dorywcza.model.service_offer.ServiceOffer;
import com.example.dorywcza.repository.ServiceOfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceOfferService {
    private final ServiceOfferRepository serviceOfferRepository;

    public ServiceOfferService(ServiceOfferRepository serviceOfferRepository) {
        this.serviceOfferRepository = serviceOfferRepository;
    }

    public List<ServiceOffer> findAll() {
        return serviceOfferRepository.findAll();
    }

    public Optional<ServiceOffer> findById(int id) {
        return serviceOfferRepository.findById(id);
    }

    public ServiceOffer addServiceOffer(ServiceOffer serviceOffer) {
        return serviceOfferRepository.save(serviceOffer);
    }
}

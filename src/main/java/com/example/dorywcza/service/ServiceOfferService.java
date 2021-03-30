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

//    public List<ServiceOffer> findAllByService() {
//        return serviceOfferRepository.findAllByServiceScheduleMondayAfternoon(false);
//    }

    public Optional<ServiceOffer> findById(Long id) {
        return serviceOfferRepository.findById(id);
    }

    public ServiceOffer addServiceOffer(ServiceOffer serviceOffer) {
        return serviceOfferRepository.save(serviceOffer);
    }

    public ServiceOffer updateServiceOffer(ServiceOffer serviceOffer, Long id) {
        if (findById(id).isEmpty()) {
            throw  new RuntimeException();
        }
        else {
            ServiceOffer serviceOfferTemp = serviceOfferRepository.getOne(id);
            serviceOffer.getOfferSchedule().setId(serviceOfferTemp.getOfferSchedule().getId());
            serviceOffer.getOfferLocation().setId(serviceOfferTemp.getOfferLocation().getId());
            serviceOffer.getDateRange().setId(serviceOfferTemp.getDateRange().getId());
        }
        serviceOffer.setId(id);

        return serviceOfferRepository.save(serviceOffer);
    }

    public void deleteServiceOffer(Long id) {
        serviceOfferRepository.deleteById(id);
    }
}

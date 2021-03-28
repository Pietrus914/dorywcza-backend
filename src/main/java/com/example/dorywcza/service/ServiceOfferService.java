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

    public List<ServiceOffer> findAllByService() {
        return serviceOfferRepository.findAllByServiceScheduleMondayAfternoon(false);
    }

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
            serviceOffer.getServiceSchedule().setId(serviceOfferTemp.getServiceSchedule().getId());
            serviceOffer.getServiceJobSalary().setId(serviceOfferTemp.getServiceJobSalary().getId());
            serviceOffer.getServiceLocation().setId(serviceOfferTemp.getServiceLocation().getId());
            serviceOffer.getServiceDateRange().setId(serviceOfferTemp.getServiceDateRange().getId());
        }
        serviceOffer.setId(id);

        return serviceOfferRepository.save(serviceOffer);
    }

    public void deleteServiceOffer(Long id) {
        serviceOfferRepository.deleteById(id);
    }
}

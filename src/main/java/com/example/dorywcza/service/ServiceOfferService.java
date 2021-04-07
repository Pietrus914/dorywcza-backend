package com.example.dorywcza.service;

import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import com.example.dorywcza.repository.ServiceOfferRepository;
import com.example.dorywcza.service.DTOExtractor.ServiceOfferDTOExtractor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceOfferService {
    private final ServiceOfferRepository serviceOfferRepository;
    private final ServiceOfferDTOExtractor serviceOfferDTOExtractor;

    public ServiceOfferService(ServiceOfferRepository serviceOfferRepository, ServiceOfferDTOExtractor serviceOfferDTOExtractor) {
        this.serviceOfferRepository = serviceOfferRepository;
        this.serviceOfferDTOExtractor = serviceOfferDTOExtractor;
    }

    public List<ServiceOffer> findAll() {
        return serviceOfferRepository.findAll();
    }

    public Optional<ServiceOffer> findById(Long id) {
        return serviceOfferRepository.findById(id);
    }

    public ServiceOffer addServiceOffer(OfferPostDTO offerPostDTO) {
        ServiceOffer serviceOffer = serviceOfferDTOExtractor.getOffer(offerPostDTO);
        return serviceOfferRepository.save(serviceOffer);
    }

    public ServiceOffer updateServiceOffer(OfferPostDTO offerPostDTO, Long id) {
        ServiceOffer serviceOffer = serviceOfferDTOExtractor.getOffer(offerPostDTO);
        if (findById(id).isEmpty()) {
            throw  new RuntimeException();
        }
        else {
            ServiceOffer serviceOfferTemp = serviceOfferRepository.getOne(id);
            serviceOffer.getOfferSchedule().setId(serviceOfferTemp.getOfferSchedule().getId());
            serviceOffer.getOfferLocation().setId(serviceOfferTemp.getOfferLocation().getId());
            serviceOffer.getDateRange().setId(serviceOfferTemp.getDateRange().getId());
            serviceOffer.getSalary().setId(serviceOfferTemp.getSalary().getId());
            serviceOffer.setDateCreated(serviceOfferTemp.getDateCreated());
        }
        serviceOffer.setId(id);

        return serviceOfferRepository.save(serviceOffer);
    }

    public void deleteServiceOffer(Long id) {
        serviceOfferRepository.deleteById(id);
    }
}

package com.example.dorywcza.service;

import com.example.dorywcza.model.OfferType;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import com.example.dorywcza.repository.ServiceOfferRepository;
import com.example.dorywcza.service.DTOExtractor.OfferDTOExtractor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceOfferService {
    private final ServiceOfferRepository serviceOfferRepository;
    private final OfferDTOExtractor offerDTOExtractor;

    public ServiceOfferService(ServiceOfferRepository serviceOfferRepository, OfferDTOExtractor offerDTOExtractor) {
        this.serviceOfferRepository = serviceOfferRepository;
        this.offerDTOExtractor = offerDTOExtractor;
    }

    public List<ServiceOffer> findAll() {
        return serviceOfferRepository.findAll();
    }

    public Optional<ServiceOffer> findById(Long id) {
        return serviceOfferRepository.findById(id);
    }

    public ServiceOffer addServiceOffer(OfferPostDTO offerPostDTO) {
        ServiceOffer serviceOffer = (ServiceOffer) offerDTOExtractor.getOfferV1(offerPostDTO, OfferType.SERVICE_OFFER);
        return serviceOfferRepository.save(serviceOffer);
    }

    public ServiceOffer updateServiceOffer(OfferPostDTO offerPostDTO, Long id) {
        Optional<ServiceOffer> foundServiceOffer = findById(id);
        if (foundServiceOffer.isEmpty()) {
            throw  new RuntimeException();
        }
        ServiceOffer serviceOfferToUpdate = foundServiceOffer.get();
        ServiceOffer updatedServiceOffer = (ServiceOffer) offerDTOExtractor.setIdsBeforeUpdate(offerPostDTO, serviceOfferToUpdate, OfferType.SERVICE_OFFER);
        return serviceOfferRepository.save(updatedServiceOffer);
    }

    public void deleteServiceOffer(Long id) {
        serviceOfferRepository.deleteById(id);
    }
}

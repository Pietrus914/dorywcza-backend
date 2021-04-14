package com.example.dorywcza.service;

import com.example.dorywcza.model.OfferType;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import com.example.dorywcza.repository.ServiceOfferRepository;
import com.example.dorywcza.service.DTOExtractor.OfferDTOExtractor;
import com.example.dorywcza.service.DTOExtractor.OfferExtractor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceOfferService {
    private final ServiceOfferRepository serviceOfferRepository;
    private final OfferDTOExtractor offerDTOExtractor;
    private final OfferExtractor offerExtractor;

    public ServiceOfferService(ServiceOfferRepository serviceOfferRepository, OfferDTOExtractor offerDTOExtractor, OfferExtractor offerExtractor) {
        this.serviceOfferRepository = serviceOfferRepository;
        this.offerDTOExtractor = offerDTOExtractor;
        this.offerExtractor = offerExtractor;
    }

    public List<OfferPostDTO> findAll() {
        return serviceOfferRepository.findAll().stream().map(offerExtractor::getOfferDTO).collect(Collectors.toList());
    }

    public Optional<OfferPostDTO> findById(Long id) {
        return serviceOfferRepository.findById(id).map(offerExtractor::getOfferDTO);
    }

    public OfferPostDTO addServiceOffer(OfferPostDTO offerPostDTO) {
        ServiceOffer serviceOffer = (ServiceOffer) offerDTOExtractor.getOfferV1(offerPostDTO, OfferType.SERVICE_OFFER);
        ServiceOffer savedServiceOffer = serviceOfferRepository.save(serviceOffer);
        return offerExtractor.getOfferDTO(savedServiceOffer);
    public ServiceOffer addServiceOffer(OfferPostDTO offerPostDTO) {
        ServiceOffer serviceOffer = serviceOfferDTOExtractor.getOffer(offerPostDTO, true, OfferType.SERVICE_OFFER);
        return serviceOfferRepository.save(serviceOffer);
    }

    public OfferPostDTO updateServiceOffer(OfferPostDTO offerPostDTO, Long id) {
        Optional<ServiceOffer> foundServiceOffer = serviceOfferRepository.findById(id);
        if (foundServiceOffer.isEmpty()) {
    public ServiceOffer updateServiceOffer(OfferPostDTO offerPostDTO, Long id) {
        ServiceOffer serviceOffer = serviceOfferDTOExtractor.getOffer(offerPostDTO, false, OfferType.SERVICE_OFFER);
        if (findById(id).isEmpty()) {
            throw  new RuntimeException();
        }
        ServiceOffer serviceOfferToUpdate = foundServiceOffer.get();
        ServiceOffer extractedServiceOffer = (ServiceOffer) offerDTOExtractor.setIdsBeforeUpdate(offerPostDTO, serviceOfferToUpdate, OfferType.SERVICE_OFFER);
        ServiceOffer updatedServiceOffer = serviceOfferRepository.save(extractedServiceOffer);
        return offerExtractor.getOfferDTO(updatedServiceOffer);
    }

    public void deleteServiceOffer(Long id) {
        serviceOfferRepository.deleteById(id);
    }
}

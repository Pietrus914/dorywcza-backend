package com.example.dorywcza.service;

import com.example.dorywcza.model.OfferType;
import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.offer.JobOfferTag;
import com.example.dorywcza.model.offer.Offer;
import com.example.dorywcza.model.offer.ServiceOfferTag;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import com.example.dorywcza.repository.ServiceOfferRepository;
import com.example.dorywcza.service.DTOExtractor.OfferDTOExtractor;
import com.example.dorywcza.service.DTOExtractor.OfferExtractor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceOfferService {
    private final ServiceOfferRepository serviceOfferRepository;
    private final OfferDTOExtractor offerDTOExtractor;
    private final OfferExtractor offerExtractor;
    private final ServiceOfferTagService serviceOfferTagService;

    public ServiceOfferService(ServiceOfferRepository serviceOfferRepository, OfferDTOExtractor offerDTOExtractor, OfferExtractor offerExtractor, ServiceOfferTagService serviceOfferTagService) {
        this.serviceOfferRepository = serviceOfferRepository;
        this.offerDTOExtractor = offerDTOExtractor;
        this.offerExtractor = offerExtractor;
        this.serviceOfferTagService = serviceOfferTagService;
    }

    public Page<OfferPostDTO> findAll(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<ServiceOffer> serviceOffers = serviceOfferRepository.findAll(pageRequest);

        return new PageImpl<>(serviceOffers
                .stream()
                .map(offer -> offerExtractor.getOfferDTO(offer, offer.getServiceOfferTags()
                        .stream()
                        .map(ServiceOfferTag::getName)
                        .collect(Collectors.toList())))
                .collect(Collectors.toList()), pageRequest, serviceOffers.getTotalElements());
    }

    public Optional<OfferPostDTO> findById(Long id) {
        return serviceOfferRepository.findById(id)
                .map(offer -> offerExtractor.getOfferDTO(offer, offer.getServiceOfferTags()
                        .stream()
                        .map(ServiceOfferTag::getName)
                        .collect(Collectors.toList())));
    }

    public OfferPostDTO addServiceOffer(OfferPostDTO offerPostDTO) {
        ServiceOffer serviceOffer = (ServiceOffer) offerDTOExtractor.getOfferV1(offerPostDTO, true, OfferType.SERVICE_OFFER);
        ServiceOffer savedServiceOffer = serviceOfferRepository.save(serviceOffer);
        List<String> tagsNames = savedServiceOffer.getServiceOfferTags()
                .stream()
                .map(ServiceOfferTag::getName)
                .collect(Collectors.toList());
        return offerExtractor.getOfferDTO(savedServiceOffer, tagsNames);
    }

    public OfferPostDTO updateServiceOffer(OfferPostDTO offerPostDTO, Long id) {
        Optional<ServiceOffer> foundServiceOffer = serviceOfferRepository.findById(id);
        if (foundServiceOffer.isEmpty()) {
            throw  new RuntimeException();
        }
        ServiceOffer serviceOfferToUpdate = foundServiceOffer.get();
        ServiceOffer extractedServiceOffer = (ServiceOffer) offerDTOExtractor.setIdsBeforeUpdate(offerPostDTO, serviceOfferToUpdate, OfferType.SERVICE_OFFER);
        serviceOfferTagService.decreaseFrequencyRateWhenTagDeletedDuringUpdate(serviceOfferToUpdate, extractedServiceOffer);
        ServiceOffer updatedServiceOffer = serviceOfferRepository.save(extractedServiceOffer);
        List<String> tagsName = updatedServiceOffer.getServiceOfferTags()
                .stream()
                .map(ServiceOfferTag::getName)
                .collect(Collectors.toList());
        return offerExtractor.getOfferDTO(updatedServiceOffer, tagsName);
    }

    public void deleteServiceOffer(Long id) {
        ServiceOffer serviceOfferToBeDeleted = serviceOfferRepository.getOne(id);
        serviceOfferTagService.decreaseFrequencyRateWhenTagDeleted(serviceOfferToBeDeleted.getServiceOfferTags());
        serviceOfferRepository.deleteById(id);
    }

    public List<OfferPostDTO> findAllByUserId(Long userId) {
        return serviceOfferRepository.findJobOfferByUserIdOrderByDateCreatedDesc(userId)
            .stream()
            .map(offer -> offerExtractor.getOfferDTO(offer, offer.getServiceOfferTags()
                .stream()
                .map(ServiceOfferTag::getName)
                .collect(Collectors.toList())))
            .collect(Collectors.toList());
    }

    @Transactional
    public Page<OfferPostDTO> findAllForIndustry(Long industryId, int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<ServiceOffer> serviceOffers = serviceOfferRepository.findServiceOfferByIndustryIdOrParentIndustryId(industryId, pageRequest);
        return new PageImpl<>(serviceOffers
                .stream()
                .map(offer -> offerExtractor.getOfferDTO(offer, offer.getServiceOfferTags()
                        .stream()
                        .map(ServiceOfferTag::getName)
                        .collect(Collectors.toList())))
                .collect(Collectors.toList()), pageRequest, serviceOffers.getTotalElements());
    }

}

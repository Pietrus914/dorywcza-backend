package com.example.dorywcza.service;


import com.example.dorywcza.model.OfferType;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.offer.JobOfferTag;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import com.example.dorywcza.repository.JobOfferRepository;
import com.example.dorywcza.service.DTOExtractor.OfferDTOExtractor;
import com.example.dorywcza.service.DTOExtractor.OfferExtractor;
import com.example.dorywcza.service.JobOfferTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobOfferService {

    private final JobOfferRepository repository;
    private final OfferDTOExtractor offerDTOExtractor;
    private final OfferExtractor offerExtractor;
    private final JobOfferTagService jobOfferTagService;

    @Autowired
    public JobOfferService(JobOfferRepository repository, OfferDTOExtractor offerDTOExtractor, OfferExtractor offerExtractor, JobOfferTagService jobOfferTagService) {
        this.repository = repository;
        this.offerDTOExtractor = offerDTOExtractor;
        this.offerExtractor = offerExtractor;
        this.jobOfferTagService = jobOfferTagService;
    }

    public Page<OfferPostDTO> findAll(int page, int size){
        Pageable pageRequest = PageRequest.of(page, size);
        Page<JobOffer> jobOffers = repository.findAll(pageRequest);

        return new PageImpl<>(jobOffers
                .stream()
                .map(offer -> offerExtractor.getOfferDTO(offer, offer.getJobOfferTags()
                        .stream()
                        .map(JobOfferTag::getName)
                        .collect(Collectors.toList())))
                .collect(Collectors.toList()), pageRequest, jobOffers.getTotalElements());
    }

    public Optional<OfferPostDTO> findById(Long id){
        return repository.findById(id)
                .map(offer -> offerExtractor.getOfferDTO(offer, offer.getJobOfferTags()
                        .stream()
                        .map(JobOfferTag::getName)
                        .collect(Collectors.toList())));
    }

    public OfferPostDTO save(OfferPostDTO offerPostDTO){
        JobOffer jobOffer = (JobOffer) offerDTOExtractor.getOfferV1(offerPostDTO, true, OfferType.JOB_OFFER);
        JobOffer savedJobOffer = repository.save(jobOffer);
        List<String> tagsNames = savedJobOffer.getJobOfferTags()
                .stream()
                .map(JobOfferTag::getName)
                .collect(Collectors.toList());
        return offerExtractor.getOfferDTO(savedJobOffer, tagsNames);
    }

    public OfferPostDTO update(OfferPostDTO offerPostDTO, Long id) {
        Optional<JobOffer> foundJobOffer = repository.findById(id);
        if (foundJobOffer.isEmpty()) {
            throw new RuntimeException();
        }
        JobOffer jobOfferToUpdate = foundJobOffer.get();
        JobOffer extractedJobOffer = (JobOffer) offerDTOExtractor.setIdsBeforeUpdate(offerPostDTO, jobOfferToUpdate, OfferType.JOB_OFFER);
        jobOfferTagService.decreaseFrequencyRateWhenTagDeletedDuringUpdate(jobOfferToUpdate, extractedJobOffer);
        JobOffer updatedJobOffer = repository.save(extractedJobOffer);
//        consider returning void or return record saved in db
//        then there will be no need to assign tags manually
        List<String> tagsNames = updatedJobOffer.getJobOfferTags()
                .stream()
                .map(JobOfferTag::getName)
                .collect(Collectors.toList());
        return offerExtractor.getOfferDTO(updatedJobOffer, tagsNames);
    }

    public void delete(Long id){
        JobOffer jobOfferToBeDeleted = repository.getOne(id);
        jobOfferTagService.decreaseFrequencyRateWhenTagDeleted(jobOfferToBeDeleted.getJobOfferTags());
        repository.deleteById(id);
    }

    public List<OfferPostDTO> findAllByUserId(Long userId) {
        return repository.findJobOfferByUserIdOrderByDateCreatedDesc(userId)
            .stream()
            .map(offer -> offerExtractor.getOfferDTO(offer, offer.getJobOfferTags()
                .stream()
                .map(JobOfferTag::getName)
                .collect(Collectors.toList())))
            .collect(Collectors.toList());
    }
}
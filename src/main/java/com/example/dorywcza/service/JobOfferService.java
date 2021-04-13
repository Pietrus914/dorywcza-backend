package com.example.dorywcza.service;


import com.example.dorywcza.model.OfferType;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.repository.JobOfferRepository;
import com.example.dorywcza.service.DTOExtractor.OfferDTOExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobOfferService {

    private final JobOfferRepository repository;
    private final OfferDTOExtractor offerDTOExtractor;

    @Autowired
    public JobOfferService(JobOfferRepository repository, OfferDTOExtractor offerDTOExtractor) {
        this.repository = repository;
        this.offerDTOExtractor = offerDTOExtractor;
    }

    public List<JobOffer> findAll(){
        return repository.findAll();
    }

    public Optional<JobOffer> findById(Long id){
        return repository.findById(id);
    }

    public JobOffer save(OfferPostDTO offerPostDTO){
        JobOffer jobOffer = (JobOffer) offerDTOExtractor.getOfferV1(offerPostDTO, OfferType.JOB_OFFER);
        return repository.save(jobOffer);
    }

    public JobOffer update(OfferPostDTO offerPostDTO, Long id) {
        Optional<JobOffer> foundJobOffer = findById(id);
        if (foundJobOffer.isEmpty()) {
            throw new RuntimeException();
        }
        JobOffer jobOfferToUpdate = foundJobOffer.get();
        JobOffer updatedJobOffer = (JobOffer) offerDTOExtractor.setIdsBeforeUpdate(offerPostDTO, jobOfferToUpdate, OfferType.JOB_OFFER);
        return repository.save(updatedJobOffer);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
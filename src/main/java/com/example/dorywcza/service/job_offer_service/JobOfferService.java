package com.example.dorywcza.service.job_offer_service;


import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.repository.job_offer_repository.JobOfferRepository;
import com.example.dorywcza.service.DTOExtractor.JobOfferDTOExtractor;
import com.example.dorywcza.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobOfferService {

    private final JobOfferRepository repository;
    private final JobOfferDTOExtractor jobOfferDTOExtractor;

    @Autowired
    public JobOfferService(JobOfferRepository repository, JobOfferDTOExtractor jobOfferDTOExtractor) {
        this.repository = repository;
        this.jobOfferDTOExtractor = jobOfferDTOExtractor;
    }

    public List<JobOffer> findAll(){
        return repository.findAll();
    }

    public Optional<JobOffer> findById(Long id){
        return repository.findById(id);
    }

    public JobOffer save(OfferPostDTO offerPostDTO){
        JobOffer jobOffer = jobOfferDTOExtractor.getOffer(offerPostDTO);
        return repository.save(jobOffer);
    }

    public JobOffer update(OfferPostDTO offerPostDTO, Long id) {
        Optional<JobOffer> foundJobOffer = findById(id);
        if (foundJobOffer.isEmpty()) {throw new RuntimeException();}
        JobOffer jobOfferToUpdate = foundJobOffer.get();
        JobOffer updatedJobOffer = jobOfferDTOExtractor.setIdsBeforeUpdate(offerPostDTO, jobOfferToUpdate);
        return repository.save(updatedJobOffer);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
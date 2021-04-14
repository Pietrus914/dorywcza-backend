package com.example.dorywcza.service;


import com.example.dorywcza.model.OfferType;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.repository.JobOfferRepository;
import com.example.dorywcza.service.DTOExtractor.OfferDTOExtractor;
import com.example.dorywcza.service.DTOExtractor.OfferExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobOfferService {

    private final JobOfferRepository repository;
    private final OfferDTOExtractor offerDTOExtractor;
    private final OfferExtractor offerExtractor;

    @Autowired
    public JobOfferService(JobOfferRepository repository, OfferDTOExtractor offerDTOExtractor, OfferExtractor offerExtractor) {
        this.repository = repository;
        this.offerDTOExtractor = offerDTOExtractor;
        this.offerExtractor = offerExtractor;
    }

    public List<OfferPostDTO> findAll(){
        return repository.findAll().stream().map(offerExtractor::getOfferDTO).collect(Collectors.toList());
    }

    public Optional<OfferPostDTO> findById(Long id){
        return repository.findById(id).map(offerExtractor::getOfferDTO);
    }

    public OfferPostDTO save(OfferPostDTO offerPostDTO){
        JobOffer jobOffer = (JobOffer) offerDTOExtractor.getOfferV1(offerPostDTO, OfferType.JOB_OFFER);
        JobOffer savedJobOffer = repository.save(jobOffer);
        return offerExtractor.getOfferDTO(savedJobOffer);
    }

    public OfferPostDTO update(OfferPostDTO offerPostDTO, Long id) {
        Optional<JobOffer> foundJobOffer = repository.findById(id);
        if (foundJobOffer.isEmpty()) {
            throw new RuntimeException();
        }
        JobOffer jobOfferToUpdate = foundJobOffer.get();
        JobOffer extractedJobOffer = (JobOffer) offerDTOExtractor.setIdsBeforeUpdate(offerPostDTO, jobOfferToUpdate, OfferType.JOB_OFFER);
        JobOffer updatedJobOffer = repository.save(extractedJobOffer);

        return offerExtractor.getOfferDTO(updatedJobOffer);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
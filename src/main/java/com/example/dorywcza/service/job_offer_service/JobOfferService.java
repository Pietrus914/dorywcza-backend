package com.example.dorywcza.service.job_offer_service;


import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.offer.JobOfferTag;
import com.example.dorywcza.repository.JobOfferTagRepository;
import com.example.dorywcza.repository.job_offer_repository.JobOfferRepository;
import com.example.dorywcza.service.DTOExtractor.JobOfferDTOExtractor;
import com.example.dorywcza.service.JobOfferTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobOfferService {

    private final JobOfferRepository repository;
    private final JobOfferDTOExtractor jobOfferDTOExtractor;
    private final JobOfferTagRepository jobOfferTagRepository ;

    @Autowired
    public JobOfferService(JobOfferRepository repository, JobOfferDTOExtractor jobOfferDTOExtractor, JobOfferTagRepository jobOfferTagRepository) {
        this.repository = repository;
        this.jobOfferDTOExtractor = jobOfferDTOExtractor;
        this.jobOfferTagRepository = jobOfferTagRepository;
    }

    public List<JobOffer> findAll(){
        return repository.findAll();
    }

    public Optional<JobOffer> findById(Long id){
        return repository.findById(id);
    }

    public JobOffer save(OfferPostDTO offerPostDTO){
        JobOffer jobOffer = jobOfferDTOExtractor.getOffer(offerPostDTO, true);
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
        JobOffer jobOfferToBeDeleted = repository.getOne(id);
        List<JobOfferTag> tagsToBeUpdated = jobOfferToBeDeleted.getJobOfferTags();
        List<JobOfferTag> updatedTags = tagsToBeUpdated
                .stream()
                .map(tag -> {tag.setFrequencyRating(tag.getFrequencyRating()-1L); return tag;})
                .collect(Collectors.toList());
        jobOfferTagRepository.saveAll(updatedTags);
        repository.deleteById(id);
    }

}
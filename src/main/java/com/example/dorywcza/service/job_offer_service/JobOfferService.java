package com.example.dorywcza.service.job_offer_service;


import com.example.dorywcza.model.offer.DTO.JobOfferPostDTO;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.offer.JobOfferTag;
import com.example.dorywcza.repository.JobOfferTagRepository;
import com.example.dorywcza.repository.job_offer_repository.JobOfferRepository;
import com.example.dorywcza.service.DTOExtractor.JobOfferDTOExtractor;
import com.example.dorywcza.service.OfferServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobOfferService {

    private final JobOfferRepository repository;
    private final JobOfferDTOExtractor jobOfferDTOExtractor;
    private final JobOfferTagRepository jobOfferTagRepository;
    private OfferServiceUtil offerServiceUtil;

    @Autowired
    public JobOfferService(JobOfferRepository repository, JobOfferDTOExtractor jobOfferDTOExtractor,
                           JobOfferTagRepository jobOfferTagRepository) {
        this.repository = repository;
        this.jobOfferDTOExtractor = jobOfferDTOExtractor;
        this.jobOfferTagRepository = jobOfferTagRepository;
        offerServiceUtil = new OfferServiceUtil();
    }

    public List<JobOffer> findAll(){
        return repository.findAll();
    }

    public Optional<JobOffer> findById(Long id){
        return repository.findById(id);
    }

    public JobOffer save(JobOfferPostDTO jobOfferPostDTO){
        JobOffer jobOffer = jobOfferDTOExtractor.getOffer(jobOfferPostDTO, true);
        return repository.save(jobOffer);
    }

    public JobOffer update(JobOfferPostDTO jobOfferPostDTO, Long id) {
        Optional<JobOffer> foundJobOffer = findById(id);
        if (foundJobOffer.isEmpty()) {throw new RuntimeException();}
        JobOffer offerCurrentlyInDB = foundJobOffer.get();
        JobOffer offerToBeSavedInDB = jobOfferDTOExtractor.getOffer(jobOfferPostDTO, false);
        jobOfferDTOExtractor.setIdsBeforeUpdate(offerToBeSavedInDB, offerCurrentlyInDB);
        decreaseFrequencyRatingForRemovedTags(offerCurrentlyInDB, offerToBeSavedInDB);
        return repository.save(offerToBeSavedInDB);
    }

    public void delete(Long id){
        JobOffer jobOfferToBeDeleted = repository.getOne(id);
        List<JobOfferTag> updatedTags = offerServiceUtil.getJobOfferTags(jobOfferToBeDeleted);
        jobOfferTagRepository.saveAll(updatedTags);
        repository.deleteById(id);
    }

//    move to Util
    private void decreaseFrequencyRatingForRemovedTags(JobOffer jobOfferToUpdate, JobOffer updatedJobOffer) {
        List<JobOfferTag> currentJobOfferTags = jobOfferToUpdate.getJobOfferTags();
        List<JobOfferTag> updatedJobOfferTags = updatedJobOffer.getJobOfferTags();
        for (JobOfferTag currentJobOfferTag : currentJobOfferTags) {
            if (!updatedJobOfferTags.contains(currentJobOfferTag)){
                currentJobOfferTag.decreaseFrequencyRating();
            }
        }
    }


}
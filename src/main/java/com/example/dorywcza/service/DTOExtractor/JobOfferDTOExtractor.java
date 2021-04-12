package com.example.dorywcza.service.DTOExtractor;

import com.example.dorywcza.model.OfferType;
import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import org.springframework.stereotype.Service;

@Service
public class JobOfferDTOExtractor {
    private final OfferDTOExtractor offerDTOExtractor;

    public JobOfferDTOExtractor(OfferDTOExtractor offerDTOExtractor) {
        this.offerDTOExtractor = offerDTOExtractor;
    }

    public JobOffer getJobOffer(OfferPostDTO offerPostDTO){
        JobOffer joboffer = (JobOffer) offerDTOExtractor.getOfferV1(offerPostDTO, OfferType.JOB_OFFER);
        return  joboffer;
    }

    public JobOffer setJobOfferIdsBeforeUpdate(OfferPostDTO offerPostDTO, JobOffer offerCurrentlyInDb){
        JobOffer joboffer = (JobOffer)offerDTOExtractor.setIdsBeforeUpdate(offerPostDTO, offerCurrentlyInDb, OfferType.JOB_OFFER);
        return joboffer;
    }
}

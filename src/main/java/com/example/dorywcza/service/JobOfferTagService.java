package com.example.dorywcza.service;

import com.example.dorywcza.model.offer.JobOfferTag;
import com.example.dorywcza.repository.JobOfferTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
public class JobOfferTagService {

    private JobOfferTagRepository repository;
    private OfferServiceUtil offerServiceUtil;

    @Autowired
    public JobOfferTagService(JobOfferTagRepository jobOfferTagRepository) {
        this.repository = jobOfferTagRepository;
        offerServiceUtil = new OfferServiceUtil();
    }

    public List<JobOfferTag> getTags(List<String> jobOfferTagsNames, boolean isNewOffer) {
        List<JobOfferTag> existingTags = repository.findJobOfferTagsByNameIn(jobOfferTagsNames);
        return offerServiceUtil.prepareTagsForSaving(jobOfferTagsNames, existingTags, isNewOffer);
    }

}

package com.example.dorywcza.service;

import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.offer.JobOfferTag;
import com.example.dorywcza.repository.JobOfferTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;


@Service
public class JobOfferTagService {

    private final JobOfferTagRepository repository;
    private final JobOfferTagsServiceUtil jobOfferTagsServiceUtil;

    @Autowired
    public JobOfferTagService(JobOfferTagRepository jobOfferTagRepository) {
        this.repository = jobOfferTagRepository;
        jobOfferTagsServiceUtil = new JobOfferTagsServiceUtil();
    }

    public List<JobOfferTag> getTags(List<String> jobOfferTagsNames, boolean isNewOffer) {
        List<JobOfferTag> existingTags = repository.findJobOfferTagsByNameIn(jobOfferTagsNames);
        return jobOfferTagsServiceUtil.prepareTagsForSaving(jobOfferTagsNames, existingTags, isNewOffer);
    }

    public List<JobOfferTag> decreaseFrequencyRateWhenTagDeleted(List<JobOfferTag> tags) {
        List<JobOfferTag> updatedTags = tags.stream().map(jobOfferTag -> {
            jobOfferTag.decreaseFrequencyRating();
            return jobOfferTag;
        })
                .collect(Collectors.toList());
        return repository.saveAll(updatedTags);
    }

    public void decreaseFrequencyRateWhenTagDeletedDuringUpdate(JobOffer jobOfferToUpdate, JobOffer updatedJobOffer) {
        List<JobOfferTag> currentJobOfferTags = jobOfferToUpdate.getJobOfferTags();
        List<JobOfferTag> updatedJobOfferTags = updatedJobOffer.getJobOfferTags();
        for (JobOfferTag currentJobOfferTag : currentJobOfferTags) {
            if (!updatedJobOfferTags.contains(currentJobOfferTag)){
                currentJobOfferTag.decreaseFrequencyRating();
            }
        }
        repository.saveAll(currentJobOfferTags);
    }

}

package com.example.dorywcza.service;


import com.example.dorywcza.model.offer.DTO.JobOfferTagDTO;
import com.example.dorywcza.model.offer.JobOfferTag;
import com.example.dorywcza.repository.JobOfferTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobOfferTagService {

    private JobOfferTagRepository repository;

    @Autowired
    public JobOfferTagService(JobOfferTagRepository jobOfferTagRepository) {
        this.repository = jobOfferTagRepository;
    }

    public List<JobOfferTag> getTags(List<String> jobOfferTagsNames, boolean isNewOffer) {
        List<JobOfferTag> existingTags = repository.findJobOfferTagsByNameIn(jobOfferTagsNames);
        if (isNewOffer) {
            increaseFrequencyRate(existingTags);
        }
        List<JobOfferTag> addedTags = createNewTags(jobOfferTagsNames, existingTags);
        addedTags.addAll(existingTags);
        return addedTags;
    }

    private List<JobOfferTag> createNewTags(List<String> jobOfferTagsNames, List<JobOfferTag> existingTags) {
        List<JobOfferTag> newTags = new ArrayList<>();
        Map<String, Boolean> mapExistingTags = getMapFromExistingTags(existingTags);
        for (String tagName : jobOfferTagsNames)
            if (!mapExistingTags.containsKey(tagName)) {
                newTags.add(new JobOfferTag(tagName, 1L));
            }
        return newTags;
    }

    private Map<String, Boolean> getMapFromExistingTags(List<JobOfferTag> existingTags) {
        Map<String, Boolean> mapExistingTags = new HashMap<>();
        for (JobOfferTag jobOfferTag : existingTags) {
            mapExistingTags.put(jobOfferTag.getName(), true);
        }
        return mapExistingTags;
    }


    private List<JobOfferTag> increaseFrequencyRate(List<JobOfferTag> existingTags) {
        return existingTags.
                stream()
                .map(jobOfferTag -> {
                    jobOfferTag.increaseFrequencyRating();
                    return jobOfferTag;
                })
                .collect(Collectors.toList());
    }

}

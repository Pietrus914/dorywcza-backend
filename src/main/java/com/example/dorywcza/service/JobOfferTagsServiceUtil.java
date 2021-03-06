package com.example.dorywcza.service;

import com.example.dorywcza.model.offer.JobOfferTag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JobOfferTagsServiceUtil {


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

    private List<JobOfferTag> createNewTags(List<String> jobOfferTagsNames, List<JobOfferTag> existingTags) {
        List<JobOfferTag> newTags = new ArrayList<>();
        Map<String, Boolean> mapExistingTags = getMapFromExistingTags(existingTags);
        for (String tagName : jobOfferTagsNames)
            if (!mapExistingTags.containsKey(tagName)) {
                newTags.add(new JobOfferTag(tagName, 1L));
            }
        return newTags;
    }

    public List<JobOfferTag> prepareTagsForSaving(List<String> jobOfferTagsNames, List<JobOfferTag> existingTags,
                                                  boolean isNewOffer){
        if (isNewOffer) {
            increaseFrequencyRate(existingTags);
        }
        List<JobOfferTag> addedTags = createNewTags(jobOfferTagsNames, existingTags);
        addedTags.addAll(existingTags);
        return addedTags;
    }

}

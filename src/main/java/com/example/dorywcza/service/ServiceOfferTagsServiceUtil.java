package com.example.dorywcza.service;

import com.example.dorywcza.model.offer.ServiceOfferTag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceOfferTagsServiceUtil {

    private Map<String, Boolean> getMapFromExistingTags(List<ServiceOfferTag> existingTags) {
        Map<String, Boolean> mapExistingTags = new HashMap<>();
        for (ServiceOfferTag ServiceOfferTag : existingTags) {
            mapExistingTags.put(ServiceOfferTag.getName(), true);
        }
        return mapExistingTags;
    }


    private List<ServiceOfferTag> increaseFrequencyRate(List<ServiceOfferTag> existingTags) {
        return existingTags.
                stream()
                .map(ServiceOfferTag -> {
                    ServiceOfferTag.increaseFrequencyRating();
                    return ServiceOfferTag;
                })
                .collect(Collectors.toList());
    }

    private List<ServiceOfferTag> createNewTags(List<String> ServiceOfferTagsNames, List<ServiceOfferTag> existingTags) {
        List<ServiceOfferTag> newTags = new ArrayList<>();
        Map<String, Boolean> mapExistingTags = getMapFromExistingTags(existingTags);
        for (String tagName : ServiceOfferTagsNames)
            if (!mapExistingTags.containsKey(tagName)) {
                newTags.add(new ServiceOfferTag(tagName, 1L));
            }
        return newTags;
    }

    public List<ServiceOfferTag> prepareTagsForSaving(List<String> ServiceOfferTagsNames, List<ServiceOfferTag> existingTags,
                                                  boolean isNewOffer){
        if (isNewOffer) {
            increaseFrequencyRate(existingTags);
        }
        List<ServiceOfferTag> addedTags = createNewTags(ServiceOfferTagsNames, existingTags);
        addedTags.addAll(existingTags);
        return addedTags;
    }

}

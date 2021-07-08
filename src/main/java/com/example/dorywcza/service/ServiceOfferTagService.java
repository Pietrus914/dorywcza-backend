package com.example.dorywcza.service;

import com.example.dorywcza.model.offer.ServiceOfferTag;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import com.example.dorywcza.repository.ServiceOfferTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceOfferTagService {

    private final ServiceOfferTagRepository repository;
    private final ServiceOfferTagsServiceUtil serviceOfferTagsServiceUtil;

    @Autowired
    public ServiceOfferTagService(ServiceOfferTagRepository serviceOfferTagRepository) {
        this.repository = serviceOfferTagRepository;
        serviceOfferTagsServiceUtil = new ServiceOfferTagsServiceUtil();
    }

    public List<ServiceOfferTag> getTags(List<String> tagsNames, boolean isNewOffer) {
        List<ServiceOfferTag> existingTags = repository.findServiceOfferTagByNameIn(tagsNames);
        return serviceOfferTagsServiceUtil.prepareTagsForSaving(tagsNames, existingTags, isNewOffer);
    }

    public List<ServiceOfferTag> decreaseFrequencyRateWhenTagDeleted(List<ServiceOfferTag> tags) {
        List<ServiceOfferTag> updatedTags = tags.stream().map(ServiceOfferTag -> {
            ServiceOfferTag.decreaseFrequencyRating();
            return ServiceOfferTag;
        })
                .collect(Collectors.toList());
        return repository.saveAll(updatedTags);
    }

    public void decreaseFrequencyRateWhenTagDeletedDuringUpdate(ServiceOffer serviceOfferToUpdate, ServiceOffer updatedServiceOffer) {
        List<ServiceOfferTag> currentServiceOfferTags = serviceOfferToUpdate.getServiceOfferTags();
        List<ServiceOfferTag> updatedServiceOfferTags = updatedServiceOffer.getServiceOfferTags();
        for (ServiceOfferTag currentServiceOfferTag : currentServiceOfferTags) {
            if (!updatedServiceOfferTags.contains(currentServiceOfferTag)){
                currentServiceOfferTag.decreaseFrequencyRating();
            }
        }
        repository.saveAll(currentServiceOfferTags);
    }
}

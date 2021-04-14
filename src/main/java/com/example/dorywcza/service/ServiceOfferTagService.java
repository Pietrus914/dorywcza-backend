package com.example.dorywcza.service;

import com.example.dorywcza.model.offer.ServiceOfferTag;
import com.example.dorywcza.repository.ServiceOfferTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOfferTagService {

    private ServiceOfferTagRepository repository;
    private ServiceOfferTagsServiceUtil serviceOfferTagsServiceUtil;

    @Autowired
    public ServiceOfferTagService(ServiceOfferTagRepository serviceOfferTagRepository) {
        this.repository = serviceOfferTagRepository;
        serviceOfferTagsServiceUtil = new ServiceOfferTagsServiceUtil();
    }

    public List<ServiceOfferTag> getTags(List<String> tagsNames, boolean isNewOffer) {
        List<ServiceOfferTag> existingTags = repository.findServiceOfferTagByNameIn(tagsNames);
        return serviceOfferTagsServiceUtil.prepareTagsForSaving(tagsNames, existingTags, isNewOffer);
    }
}

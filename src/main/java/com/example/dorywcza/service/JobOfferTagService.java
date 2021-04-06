package com.example.dorywcza.service;

import com.example.dorywcza.model.offer.DTO.JobOfferTagDTO;
import com.example.dorywcza.model.offer.JobOfferTag;
import com.example.dorywcza.repository.JobOfferTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class JobOfferTagService  {

    private JobOfferTagRepository jobOfferTagRepository;

    @Autowired
    public JobOfferTagService(JobOfferTagRepository jobOfferTagRepository) {
        this.jobOfferTagRepository = jobOfferTagRepository;
    }

    public List<JobOfferTag> getTagsById(List<Long> jobOfferTagsIds) {
        return jobOfferTagRepository.findAllById(jobOfferTagsIds);
    }

}

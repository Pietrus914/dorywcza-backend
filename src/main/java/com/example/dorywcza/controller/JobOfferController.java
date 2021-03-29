package com.example.dorywcza.controller;

import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.job_offer.JobOfferRequestWrapper;
import com.example.dorywcza.service.job_offer_service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobOfferController {

    private JobOfferService jobOfferService;


    @Autowired
    public JobOfferController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }


    @GetMapping("/jobs")
    public List<JobOffer> findAll(){
        return jobOfferService.findAll();
    }

    @PostMapping("/jobs")
    public JobOffer saveJobOffer(@RequestBody JobOfferRequestWrapper jobOfferRequestWrapper) {
        return jobOfferService.saveJobOffer(jobOfferRequestWrapper);
    }
}

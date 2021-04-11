package com.example.dorywcza.controller;

import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.offer.DTO.JobOfferPostDTO;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.service.job_offer_service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JobOfferController {

    private JobOfferService jobOfferService;


    @Autowired
    public JobOfferController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }


    @GetMapping("/jobs")
    public List<JobOffer> findAll(){
        List<JobOffer> jobOffers = jobOfferService.findAll();
        return jobOffers;
    }

    @GetMapping("/jobs/{id}")
    public Optional<JobOffer> findById(@PathVariable Long id) {
        return jobOfferService.findById(id);
    }

    @PostMapping("/jobs")
    public JobOffer saveJobOffer(@RequestBody JobOfferPostDTO jobOfferPostDTO) {
        return jobOfferService.save(jobOfferPostDTO);
    }

    @PutMapping("/jobs/{id}")
    public JobOffer updateJobOffer (@RequestBody JobOfferPostDTO jobOfferPostDTO, @PathVariable Long id){
        return jobOfferService.update(jobOfferPostDTO, id);
    }

    @DeleteMapping("/jobs/{id}")
    public void deleteJobOffer(@PathVariable Long id) {
        jobOfferService.delete(id);
    }

}


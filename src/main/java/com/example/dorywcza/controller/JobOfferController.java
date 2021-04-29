package com.example.dorywcza.controller;

import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JobOfferController {

    private final JobOfferService jobOfferService;


    @Autowired
    public JobOfferController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @GetMapping("/jobs")
    public List<OfferPostDTO> findAll() {
        return jobOfferService.findAll();
    }

    @GetMapping("/jobs/{id}")
    public Optional<OfferPostDTO> findById(@PathVariable Long id) {
        return jobOfferService.findById(id);
    }

    @PostMapping("/jobs")
    public OfferPostDTO saveJobOffer(@RequestBody OfferPostDTO offerPostDTO) {
        return jobOfferService.save(offerPostDTO);
    }

    @PutMapping("/jobs/{id}")
    public OfferPostDTO updateJobOffer (@RequestBody OfferPostDTO offerPostDTO, @PathVariable Long id){
        return jobOfferService.update(offerPostDTO, id);
    }

    @DeleteMapping("/jobs/{id}")
    public void deleteJobOffer(@PathVariable Long id) {
        jobOfferService.delete(id);
    }

}


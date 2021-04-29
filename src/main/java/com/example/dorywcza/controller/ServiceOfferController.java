package com.example.dorywcza.controller;

import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceOfferController {
    private final ServiceOfferService serviceOfferService;

    public ServiceOfferController(ServiceOfferService serviceOfferService) {
        this.serviceOfferService = serviceOfferService;
    }

    @GetMapping("/service-offers")
    public List<OfferPostDTO> findAll() {
        List<OfferPostDTO> a = serviceOfferService.findAll();
        return serviceOfferService.findAll();
    }


    @GetMapping("/service-offers/{id}")
    public Optional<OfferPostDTO> findById(@PathVariable Long id) {
        return serviceOfferService.findById(id);
    }

    @GetMapping("/add-service-offer")
    public void getAddServiceOffer() {}

    @PostMapping("/add-service-offer")
    public OfferPostDTO addServiceOffer(@RequestBody OfferPostDTO offerPostDTO) {
        return serviceOfferService.addServiceOffer(offerPostDTO);
    }

    @PutMapping("/update-service-offer/{id}")
    public OfferPostDTO updateServiceOffer(@RequestBody OfferPostDTO offerPostDTO, @PathVariable Long id) {
        return serviceOfferService.updateServiceOffer(offerPostDTO, id);
    }

    @DeleteMapping("/service-offers/{id}")
    public void deleteServiceOffer(@PathVariable Long id) {
        serviceOfferService.deleteServiceOffer(id);
    }
}

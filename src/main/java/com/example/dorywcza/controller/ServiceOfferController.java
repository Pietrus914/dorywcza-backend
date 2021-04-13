package com.example.dorywcza.controller;

import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import com.example.dorywcza.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ServiceOfferController {
    private final ServiceOfferService serviceOfferService;

    public ServiceOfferController(ServiceOfferService serviceOfferService) {
        this.serviceOfferService = serviceOfferService;
    }

    @GetMapping("/service-offers")
    public List<OfferPostDTO> findAll() {
        return serviceOfferService.findAll();
    }


    @GetMapping("/service-offers/{id}")
    public Optional<ServiceOffer> findById(@PathVariable Long id) {
        return serviceOfferService.findById(id);
    }

    @GetMapping("/add-service-offer")
    public void getAddServiceOffer() {}

    @PostMapping("/add-service-offer")
    public ServiceOffer addServiceOffer(@RequestBody OfferPostDTO offerPostDTO) {
        return serviceOfferService.addServiceOffer(offerPostDTO);
    }

    @PutMapping("/update-service-offer/{id}")
    public ServiceOffer updateServiceOffer(@RequestBody OfferPostDTO offerPostDTO, @PathVariable Long id) {
        return serviceOfferService.updateServiceOffer(offerPostDTO, id);
    }

    @DeleteMapping("/service-offers/{id}")
    public void deleteServiceOffer(@PathVariable Long id) {
        serviceOfferService.deleteServiceOffer(id);
    }
}

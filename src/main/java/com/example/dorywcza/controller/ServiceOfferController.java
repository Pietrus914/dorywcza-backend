package com.example.dorywcza.controller;

import com.example.dorywcza.model.service_offer.ServiceOffer;
import com.example.dorywcza.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ServiceOfferController {
    private final ServiceOfferService serviceOfferService;
    private final ServiceDataRangeService serviceDataRangeService;
    private final ServiceJobSalaryService serviceJobSalaryService;
    private final ServiceLocationService serviceLocationService;
    private final ServiceScheduleService serviceScheduleService;

    public ServiceOfferController(ServiceOfferService serviceOfferService, ServiceDataRangeService serviceDataRangeService, ServiceJobSalaryService serviceJobSalaryService, ServiceLocationService serviceLocationService, ServiceScheduleService serviceScheduleService) {
        this.serviceOfferService = serviceOfferService;
        this.serviceDataRangeService = serviceDataRangeService;
        this.serviceJobSalaryService = serviceJobSalaryService;
        this.serviceLocationService = serviceLocationService;
        this.serviceScheduleService = serviceScheduleService;
    }

    @GetMapping("/service-offers")
    public List<ServiceOffer> findAll() {
        return serviceOfferService.findAll();
    }

    @GetMapping("/service-offers/{id}")
    public Optional<ServiceOffer> findById(@PathVariable int id) {
        return serviceOfferService.findById(id);
    }

    @GetMapping("/add-service-offer")
    public void getAddServiceOffer() {}

    @PostMapping("/add-service-offer")
    public ServiceOffer addServiceOffer(@RequestBody ServiceOffer serviceOffer) {
        serviceDataRangeService.addServiceDataRange(serviceOffer.getServiceDateRange());
        serviceJobSalaryService.addServiceJobSalary(serviceOffer.getServiceJobSalary());
        serviceLocationService.addServiceLocation(serviceOffer.getServiceLocation());
        serviceScheduleService.addServiceSchedule(serviceOffer.getServiceSchedule());
        return serviceOfferService.addServiceOffer(serviceOffer);
    }
}

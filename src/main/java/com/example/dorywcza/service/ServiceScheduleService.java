package com.example.dorywcza.service;

import com.example.dorywcza.model.service_offer.ServiceSchedule;
import com.example.dorywcza.repository.ServiceScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceScheduleService {
    private final ServiceScheduleRepository serviceScheduleRepository;

    public ServiceScheduleService(ServiceScheduleRepository serviceScheduleRepository) {
        this.serviceScheduleRepository = serviceScheduleRepository;
    }

    public void addServiceSchedule(ServiceSchedule serviceSchedule) {
        serviceScheduleRepository.save(serviceSchedule);
    }
}

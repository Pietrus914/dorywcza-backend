package com.example.dorywcza.repository;

import com.example.dorywcza.model.service_offer.ServiceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceScheduleRepository extends JpaRepository<ServiceSchedule, Long> {
}

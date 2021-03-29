package com.example.dorywcza.repository;

import com.example.dorywcza.model.offer.OfferSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceScheduleRepository extends JpaRepository<OfferSchedule, Long> {
}

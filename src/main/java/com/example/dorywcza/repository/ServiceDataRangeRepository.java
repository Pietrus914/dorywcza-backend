package com.example.dorywcza.repository;

import com.example.dorywcza.model.offer.DateRange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceDataRangeRepository extends JpaRepository<DateRange, Long> {
}

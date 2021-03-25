package com.example.dorywcza.repository;

import com.example.dorywcza.model.service_offer.ServiceLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceLocationRepository extends JpaRepository<ServiceLocation, Integer> {
}

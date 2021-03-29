package com.example.dorywcza.repository;

import com.example.dorywcza.model.offer.OfferLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceLocationRepository extends JpaRepository<OfferLocation, Long> {
}

package com.example.dorywcza.repository;

import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ServiceOfferRepository extends JpaRepository<ServiceOffer, Long> {

    List<ServiceOffer> findJobOfferByUserIdOrderByDateCreatedDesc(Long userId);
}

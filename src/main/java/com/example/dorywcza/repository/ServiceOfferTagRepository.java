package com.example.dorywcza.repository;

import com.example.dorywcza.model.offer.ServiceOfferTag;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceOfferTagRepository extends JpaRepository <ServiceOfferTag, Long>{

    List<ServiceOfferTag> findServiceOfferTagByNameIn(java.util.List list);
}

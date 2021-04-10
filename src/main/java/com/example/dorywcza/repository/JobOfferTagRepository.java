package com.example.dorywcza.repository;

import com.example.dorywcza.model.offer.JobOfferTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobOfferTagRepository extends JpaRepository<JobOfferTag, Long> {

    List<JobOfferTag> findJobOfferTagsByNameIn(java.util.List list);

}

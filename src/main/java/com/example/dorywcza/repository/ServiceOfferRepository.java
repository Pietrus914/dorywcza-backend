package com.example.dorywcza.repository;

import com.example.dorywcza.model.service_offer.ServiceOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Date;

public interface ServiceOfferRepository extends JpaRepository<ServiceOffer, Long> {
    List<ServiceOffer> findByIndustryIdAndOfferLocation_xPositionAndOfferLocation_yPositionAndDateRangeStartDateLessThanEqualAndHasExperienceOrHasExperienceIsTrue(Long industryId, double xPosition, double yPosition, Date endDate, boolean hasExperience);
}

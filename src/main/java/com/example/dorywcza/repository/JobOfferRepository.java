package com.example.dorywcza.repository;


import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.offer.Offer;
import com.example.dorywcza.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

    List<JobOffer> findJobOfferByUserIdOrderByDateCreatedDesc(Long userId);


    @Query(value ="SELECT * FROM  JOB_OFFER " +
            "LEFT JOIN INDUSTRY  ON  JOB_OFFER.INDUSTRY_ID = INDUSTRY.INDUSTRY_ID" +
            " WHERE JOB_OFFER.INDUSTRY_ID = :industryId OR INDUSTRY.PARENT_ID = :industryId",
            countQuery = "SELECT count (*) FROM JOB_OFFER LEFT JOIN INDUSTRY  ON  JOB_OFFER.INDUSTRY_ID = INDUSTRY.INDUSTRY_ID " +
                     "WHERE JOB_OFFER.INDUSTRY_ID = :industryId OR INDUSTRY.PARENT_ID = :industryId",
            nativeQuery = true)
    Page<JobOffer> findJobOfferByIndustryIdOrParentIndustryId(@Param("industryId") Long industryId, Pageable pageRequest);

}

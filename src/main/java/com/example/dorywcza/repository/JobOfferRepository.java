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


    @Query(value = "SELECT * FROM JOB_OFFER WHERE INDUSTRY_ID IN (" +
            "SELECT INDUSTRY_ID FROM (" +
            "WITH RECURSIVE RESULTS ( INDUSTRY_ID, NAME,  PARENT_ID ) AS(" +
            "SELECT INDUSTRY.INDUSTRY_ID, INDUSTRY.NAME, INDUSTRY.PARENT_ID " +
            "FROM INDUSTRY WHERE INDUSTRY_ID = :industryId " +
            "UNION  ALL " +
            "SELECT INDUSTRY.INDUSTRY_ID, INDUSTRY.NAME, INDUSTRY.PARENT_ID " +
            "FROM INDUSTRY INNER JOIN RESULTS ON INDUSTRY.PARENT_ID = RESULTS.INDUSTRY_ID) " +
            "SELECT * FROM RESULTS) AS INDUSTRY_IDS)",
            countQuery = "SELECT COUNT(*) FROM JOB_OFFER WHERE INDUSTRY_ID IN (" +
            "SELECT INDUSTRY_ID FROM (" +
            "WITH RECURSIVE RESULTS ( INDUSTRY_ID, NAME,  PARENT_ID ) AS(" +
            "SELECT INDUSTRY.INDUSTRY_ID, INDUSTRY.NAME, INDUSTRY.PARENT_ID " +
            "FROM INDUSTRY WHERE INDUSTRY_ID = :industryId " +
            "UNION  ALL " +
            "SELECT INDUSTRY.INDUSTRY_ID, INDUSTRY.NAME, INDUSTRY.PARENT_ID " +
            "FROM INDUSTRY INNER JOIN RESULTS ON INDUSTRY.PARENT_ID = RESULTS.INDUSTRY_ID) " +
            "SELECT * FROM RESULTS) AS INDUSTRY_IDS)",
            nativeQuery = true)
    Page<JobOffer> findJobOfferByIndustryIdOrParentIndustryId(@Param("industryId") Long industryId, Pageable pageRequest);

}

package com.example.dorywcza.repository;

import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ServiceOfferRepository extends JpaRepository<ServiceOffer, Long> {

    List<ServiceOffer> findJobOfferByUserIdOrderByDateCreatedDesc(Long userId);
//
//    @Query(value ="SELECT * FROM  SERVICE_OFFER " +
//            "LEFT JOIN INDUSTRY  ON  SERVICE_OFFER.INDUSTRY_ID = INDUSTRY.INDUSTRY_ID" +
//            " WHERE SERVICE_OFFER.INDUSTRY_ID = :industryId OR INDUSTRY.PARENT_ID = :industryId",
//            countQuery = "SELECT count (*) FROM SERVICE_OFFER LEFT JOIN INDUSTRY  ON  SERVICE_OFFER.INDUSTRY_ID = INDUSTRY.INDUSTRY_ID" +
//                    "WHERE SERVICE_OFFER.INDUSTRY_ID = :industryId OR INDUSTRY.PARENT_ID = :industryId",
//            nativeQuery = true)

    @Query(value = "SELECT * FROM SERVICE_OFFER WHERE INDUSTRY_ID IN (" +
            "SELECT INDUSTRY_ID FROM (" +
            "WITH RECURSIVE RESULTS ( INDUSTRY_ID, NAME,  PARENT_ID ) AS(" +
            "SELECT INDUSTRY.INDUSTRY_ID, INDUSTRY.NAME, INDUSTRY.PARENT_ID " +
            "FROM INDUSTRY WHERE INDUSTRY_ID = :industryId " +
            "UNION  ALL " +
            "SELECT INDUSTRY.INDUSTRY_ID, INDUSTRY.NAME, INDUSTRY.PARENT_ID " +
            "FROM INDUSTRY INNER JOIN RESULTS ON INDUSTRY.PARENT_ID = RESULTS.INDUSTRY_ID) " +
            "SELECT * FROM RESULTS) AS INDUSTRY_IDS)",
        countQuery = "SELECT * FROM SERVICE_OFFER WHERE INDUSTRY_ID IN (" +
            "SELECT INDUSTRY_ID FROM (\n" +
            "WITH RECURSIVE RESULTS ( INDUSTRY_ID, NAME,  PARENT_ID ) AS(" +
            "SELECT INDUSTRY.INDUSTRY_ID, INDUSTRY.NAME, INDUSTRY.PARENT_ID " +
            "FROM INDUSTRY WHERE INDUSTRY_ID = :industryId " +
            "UNION  ALL " +
            "SELECT INDUSTRY.INDUSTRY_ID, INDUSTRY.NAME, INDUSTRY.PARENT_ID " +
            "FROM INDUSTRY INNER JOIN RESULTS ON INDUSTRY.PARENT_ID = RESULTS.INDUSTRY_ID) " +
            "SELECT * FROM RESULTS) AS INDUSTRY_IDS)",
        nativeQuery = true)
    Page<ServiceOffer> findServiceOfferByIndustryIdOrParentIndustryId(@Param("industryId") Long industryId, Pageable pageRequest);
}

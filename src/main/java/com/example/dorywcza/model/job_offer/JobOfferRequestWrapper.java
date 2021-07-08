package com.example.dorywcza.model.job_offer;


import com.example.dorywcza.model.offer.Salary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferRequestWrapper {

    private JobOffer jobOffer;
    private Long industryId;
    private Long salaryTimeUnitId;
}
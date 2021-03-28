package com.example.dorywcza.model.job_offer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferRequestWrapper {

    private JobOffer jobOffer;
    private Long industryId;
    private Long salaryTimeUnitId;
    private JobSalary jobSalary;

}
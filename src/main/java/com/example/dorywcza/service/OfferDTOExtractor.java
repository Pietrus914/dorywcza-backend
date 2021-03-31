package com.example.dorywcza.service;

import com.example.dorywcza.exceptions.RecordNotFound;
import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.offer.*;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


//    Abstract class needed so methods can be used for both service & job offers


@Service
public class OfferDTOExtractor {

    private IndustryService industryService;
    private SalaryTimeUnitService salaryTimeUnitService;

    @Autowired
    public OfferDTOExtractor(IndustryService industryService,
                           SalaryTimeUnitService salaryTimeUnitService) {
        this.industryService =  industryService;
        this.salaryTimeUnitService = salaryTimeUnitService;
    }

    private Salary getSalary(OfferPostDTO offerPostDTO){
        Salary salary = offerPostDTO.getSalary();
        SalaryTimeUnit salaryTimeUnit = salaryTimeUnitService.findSalaryTimeUnitById(offerPostDTO.getSalaryTimeUnitId());
        if (salary.getId() != null) {
            throw new RecordNotFound(HttpStatus.UNPROCESSABLE_ENTITY, "Incorrect data");
        }
        salary.setSalaryTimeUnit(salaryTimeUnit);
        return salary;
    }

    private DateRange getDateRange(OfferPostDTO offerPostDTO){
        DateRange dateRange = offerPostDTO.getDateRange();
        if (dateRange.getId() != null)
            throw new RecordNotFound(HttpStatus.UNPROCESSABLE_ENTITY, "Incorrect data");
        return dateRange;
    }

    private OfferLocation getOfferLocation(OfferPostDTO offerPostDTO)  {
        OfferLocation offerLocation = offerPostDTO.getOfferLocation();
        if (offerLocation.getId() != null)
            throw new RecordNotFound(HttpStatus.UNPROCESSABLE_ENTITY, "Incorrect data");
        return offerLocation;
    }

    private OfferSchedule getOfferSchedule(OfferPostDTO offerPostDTO) {
        OfferSchedule offerSchedule = offerPostDTO.getOfferSchedule();
        if (offerSchedule.getId() != null)
            throw new RecordNotFound(HttpStatus.UNPROCESSABLE_ENTITY, "Incorrect data");
        return offerSchedule;
    }


    public JobOffer getOffer(OfferPostDTO offerPostDTO){
        Salary salary = getSalary(offerPostDTO);
        DateRange dateRange = getDateRange(offerPostDTO);
        OfferLocation offerLocation = getOfferLocation(offerPostDTO);
        OfferSchedule offerSchedule = getOfferSchedule(offerPostDTO);
        Industry industry = industryService.findById(offerPostDTO.getIndustryId());
        return new JobOffer(offerPostDTO.getTitle(), offerPostDTO.getDescription(),
                offerPostDTO.getUserId(), offerPostDTO.isHasExperience(),
                offerLocation, dateRange, industry, salary, offerSchedule);
    }
}

package com.example.dorywcza.service.DTOExtractor;


import com.example.dorywcza.model.OfferType;
import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.offer.*;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import com.example.dorywcza.model.user.User;
import com.example.dorywcza.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//    Abstract class needed so methods can be used for both service & job offers


@Service
public class OfferDTOExtractor {

    final IndustryService industryService;
    private final SalaryTimeUnitService salaryTimeUnitService;
    private final UserService userService;
    private final ServiceOfferTagService serviceOfferTagService;
    private final JobOfferTagService jobOfferTagService;


    @Autowired
    public OfferDTOExtractor(IndustryService industryService, SalaryTimeUnitService salaryTimeUnitService,
                             UserService userService, ServiceOfferTagService serviceOfferTagService, JobOfferTagService jobOfferTagService) {
        this.industryService =  industryService;
        this.salaryTimeUnitService = salaryTimeUnitService;
        this.userService = userService;
        this.serviceOfferTagService = serviceOfferTagService;
        this.jobOfferTagService = jobOfferTagService;
    }

    private SalaryTimeUnit getSalaryTimeUnit(OfferPostDTO offerPostDTO) {
        SalaryTimeUnit salaryTimeUnit = salaryTimeUnitService.findSalaryTimeUnitById(offerPostDTO.getSalaryTimeUnitDTO().getId());
        return salaryTimeUnit;
    }

    private Salary getSalary(OfferPostDTO offerPostDTO){
        Salary salary = new Salary(offerPostDTO.getSalaryDTO().getMinSalary(), offerPostDTO.getSalaryDTO().getMaxSalary());
        SalaryTimeUnit salaryTimeUnit = getSalaryTimeUnit(offerPostDTO);
        salary.setSalaryTimeUnit(salaryTimeUnit);
        return salary;
    }

    private DateRange getDateRange(OfferPostDTO offerPostDTO){
        DateRange dateRange = new DateRange(offerPostDTO.getDateRangeDTO().getStartDate(),
                offerPostDTO.getDateRangeDTO().getEndDate());
        return dateRange;
    }

    private OfferLocation getOfferLocation(OfferPostDTO offerPostDTO)  {
        OfferLocation offerLocation = new OfferLocation(offerPostDTO.getOfferLocationDTO().getXPosition(),
                offerPostDTO.getOfferLocationDTO().getYPosition());
        return offerLocation;
    }

    private OfferSchedule getOfferSchedule(OfferPostDTO offerPostDTO) {
        OfferSchedule offerSchedule = new OfferSchedule(offerPostDTO.getOfferScheduleDTO());
        return offerSchedule;
    }

    private Industry getIndustry(OfferPostDTO offerPostDTO) {
        Industry industry = industryService.findById(offerPostDTO.getIndustryDTO().getId());
        return industry;
    }

    public Offer getOfferV1(OfferPostDTO offerPostDTO, boolean isNewOffer, OfferType offerType){
        List<String> tagsNames = offerPostDTO.getTagsNames();
        Salary salary = getSalary(offerPostDTO);
        DateRange dateRange = getDateRange(offerPostDTO);
        OfferLocation offerLocation = getOfferLocation(offerPostDTO);
        OfferSchedule offerSchedule = getOfferSchedule(offerPostDTO);
        User user = userService.findUserById(offerPostDTO.getUserId());
        Industry industry = getIndustry(offerPostDTO);
        switch (offerType){
            case JOB_OFFER:
                List<JobOfferTag> jobOfferTags = jobOfferTagService.getTags(tagsNames, isNewOffer);
                return new JobOffer(offerPostDTO.getTitle(), offerPostDTO.getDescription(),
                    user, offerPostDTO.isHasExperience(),
                    offerLocation, dateRange, industry, salary, offerSchedule, jobOfferTags);
            case SERVICE_OFFER:
                List<ServiceOfferTag> serviceOfferTags = serviceOfferTagService.getTags(tagsNames, isNewOffer);
                return new ServiceOffer(offerPostDTO.getTitle(), offerPostDTO.getDescription(),
                        user, offerPostDTO.isHasExperience(),
                        offerLocation, dateRange, industry, salary, offerSchedule, serviceOfferTags);
        }
        throw new IllegalArgumentException();
    }



    public Offer setIdsBeforeUpdate(OfferPostDTO offerPostDTO, Offer offerCurrentlyInDB,
                                    OfferType offerType) {
        Offer offerToBeSavedInDB = getOfferV1(offerPostDTO, false, offerType);
        offerToBeSavedInDB.getOfferSchedule().setId(offerCurrentlyInDB.getOfferSchedule().getId());
        offerToBeSavedInDB.getOfferLocation().setId(offerCurrentlyInDB.getOfferLocation().getId());
        offerToBeSavedInDB.getDateRange().setId(offerCurrentlyInDB.getDateRange().getId());
        offerToBeSavedInDB.getSalary().setId(offerCurrentlyInDB.getSalary().getId());
        offerToBeSavedInDB.setDateCreated(offerCurrentlyInDB.getDateCreated());
        offerToBeSavedInDB.setId(offerCurrentlyInDB.getId());
        return offerToBeSavedInDB;
    }

}

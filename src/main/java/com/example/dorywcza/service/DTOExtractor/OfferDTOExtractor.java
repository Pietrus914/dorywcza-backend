package com.example.dorywcza.service.DTOExtractor;


import com.example.dorywcza.model.OfferType;
import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.offer.*;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import com.example.dorywcza.model.user.User;
import com.example.dorywcza.service.IndustryService;
import com.example.dorywcza.service.SalaryTimeUnitService;
import com.example.dorywcza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//    Abstract class needed so methods can be used for both service & job offers


@Service
public class OfferDTOExtractor {

    final IndustryService industryService;
    private final SalaryTimeUnitService salaryTimeUnitService;
    final UserService userService;


    @Autowired
    public OfferDTOExtractor(IndustryService industryService, SalaryTimeUnitService salaryTimeUnitService,
                             UserService userService) {
        this.industryService =  industryService;
        this.salaryTimeUnitService = salaryTimeUnitService;
        this.userService = userService;
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

    public Offer getOfferV1(OfferPostDTO offerPostDTO, OfferType offerType){
        Salary salary = getSalary(offerPostDTO);
        DateRange dateRange = getDateRange(offerPostDTO);
        OfferLocation offerLocation = getOfferLocation(offerPostDTO);
        OfferSchedule offerSchedule = getOfferSchedule(offerPostDTO);
        User user = userService.findUserById(offerPostDTO.getUserId());
        Industry industry = getIndustry(offerPostDTO);
        switch (offerType){
            case JOB_OFFER:
                return new JobOffer(offerPostDTO.getTitle(), offerPostDTO.getDescription(),
                    user, offerPostDTO.isHasExperience(),
                    offerLocation, dateRange, industry, salary, offerSchedule);
            case SERVICE_OFFER:
                return new ServiceOffer(offerPostDTO.getTitle(), offerPostDTO.getDescription(),
                        user, offerPostDTO.isHasExperience(),
                        offerLocation, dateRange, industry, salary, offerSchedule);
        }
        throw new IllegalArgumentException();
    }



    public Offer setIdsBeforeUpdate(OfferPostDTO offerPostDTO, Offer offerCurrentlyInDB,
                                    OfferType offerType) {
        Offer offerToBeSavedInDB = getOfferV1(offerPostDTO, offerType);
        offerToBeSavedInDB.getOfferSchedule().setId(offerCurrentlyInDB.getOfferSchedule().getId());
        offerToBeSavedInDB.getOfferLocation().setId(offerCurrentlyInDB.getOfferLocation().getId());
        offerToBeSavedInDB.getDateRange().setId(offerCurrentlyInDB.getDateRange().getId());
        offerToBeSavedInDB.getSalary().setId(offerCurrentlyInDB.getSalary().getId());
        offerToBeSavedInDB.setDateCreated(offerCurrentlyInDB.getDateCreated());
        offerToBeSavedInDB.setId(offerCurrentlyInDB.getId());
        return offerToBeSavedInDB;
    }

}

package com.example.dorywcza.service.DTOExtractor;


import com.example.dorywcza.model.offer.*;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.service.IndustryService;
import com.example.dorywcza.service.SalaryTimeUnitService;
import com.example.dorywcza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//    Abstract class needed so methods can be used for both service & job offers


@Service
public abstract class OfferDTOExtractor {

    final IndustryService industryService;
    private final SalaryTimeUnitService salaryTimeUnitService;
    final UserService userService;


    @Autowired
    public OfferDTOExtractor(IndustryService industryService,
                             SalaryTimeUnitService salaryTimeUnitService, UserService userService) {
        this.industryService =  industryService;
        this.salaryTimeUnitService = salaryTimeUnitService;
        this.userService = userService;
    }

    Salary getSalary(OfferPostDTO offerPostDTO){
        Salary salary = new Salary(offerPostDTO.getSalaryDTO().getMinSalary(), offerPostDTO.getSalaryDTO().getMaxSalary());
        SalaryTimeUnit salaryTimeUnit = salaryTimeUnitService.findSalaryTimeUnitById(offerPostDTO.getSalaryTimeUnitId());
        salary.setSalaryTimeUnit(salaryTimeUnit);
        return salary;
    }

    DateRange getDateRange(OfferPostDTO offerPostDTO){
        DateRange dateRange = new DateRange(offerPostDTO.getDateRangeDTO().getStartDate(),
                offerPostDTO.getDateRangeDTO().getEndDate());
        return dateRange;
    }

    OfferLocation getOfferLocation(OfferPostDTO offerPostDTO)  {
        OfferLocation offerLocation = new OfferLocation(offerPostDTO.getOfferLocationDTO().getXPosition(),
                offerPostDTO.getOfferLocationDTO().getYPosition());
        return offerLocation;
    }

    OfferSchedule getOfferSchedule(OfferPostDTO offerPostDTO) {
        OfferSchedule offerSchedule = new OfferSchedule(offerPostDTO.getOfferScheduleDTO());
        return offerSchedule;
    }


}

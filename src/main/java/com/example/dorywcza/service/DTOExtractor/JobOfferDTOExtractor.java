package com.example.dorywcza.service.DTOExtractor;

import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.offer.*;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.user.User;
import com.example.dorywcza.service.IndustryService;
import com.example.dorywcza.service.SalaryTimeUnitService;
import com.example.dorywcza.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class JobOfferDTOExtractor extends OfferDTOExtractor{
    public JobOfferDTOExtractor(IndustryService industryService, SalaryTimeUnitService salaryTimeUnitService, UserService userService) {
        super(industryService, salaryTimeUnitService, userService);
    }

    public JobOffer getOffer(OfferPostDTO offerPostDTO){
        Salary salary = getSalary(offerPostDTO);
        DateRange dateRange = getDateRange(offerPostDTO);
        OfferLocation offerLocation = getOfferLocation(offerPostDTO);
        OfferSchedule offerSchedule = getOfferSchedule(offerPostDTO);
        User user = userService.findById(offerPostDTO.getUserId()).get();
        Industry industry = industryService.findById(offerPostDTO.getIndustryId());
        return new JobOffer(offerPostDTO.getTitle(), offerPostDTO.getDescription(),
                user, offerPostDTO.isHasExperience(),
                offerLocation, dateRange, industry, salary, offerSchedule);
    }
}

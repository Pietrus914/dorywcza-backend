package com.example.dorywcza.service.DTOExtractor;

import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.offer.*;
import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.user.User;
import com.example.dorywcza.model.user.UserDTO;
import com.example.dorywcza.service.IndustryService;
import com.example.dorywcza.service.JobOfferTagService;
import com.example.dorywcza.service.SalaryTimeUnitService;
import com.example.dorywcza.service.UserService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class JobOfferDTOExtractor extends OfferDTOExtractor{

    private final JobOfferTagService jobOfferTagService;

    public JobOfferDTOExtractor(IndustryService industryService, SalaryTimeUnitService salaryTimeUnitService,
                                UserService userService, JobOfferTagService jobOfferTagService) {
        super(industryService, salaryTimeUnitService, userService);
        this.jobOfferTagService = jobOfferTagService;
    }

    public JobOffer getOffer(OfferPostDTO offerPostDTO, boolean isNewOffer){
        Salary salary = getSalary(offerPostDTO);
        DateRange dateRange = getDateRange(offerPostDTO);
        OfferLocation offerLocation = getOfferLocation(offerPostDTO);
        OfferSchedule offerSchedule = getOfferSchedule(offerPostDTO);
        UserDTO userDto = userService.findById(offerPostDTO.getUserId()).get();
        User user = userService.convert(userDto);
        Industry industry = industryService.findById(offerPostDTO.getIndustryId());
        List<JobOfferTag> jobOfferTags = jobOfferTagService.getTags(offerPostDTO.getJobOfferTagsNames(), isNewOffer);
        JobOffer jobOffer =  new JobOffer(offerPostDTO.getTitle(), offerPostDTO.getDescription(),
                user, offerPostDTO.isHasExperience(),
                offerLocation, dateRange, industry, salary, offerSchedule);
        jobOffer.setJobOfferTags(jobOfferTags);
        return jobOffer;
    }



    public JobOffer setIdsBeforeUpdate(OfferPostDTO offerPostDTO, JobOffer offerCurrentlyInDB) {
        JobOffer offerToBeSavedInDB = getOffer(offerPostDTO, false);
        offerToBeSavedInDB.getOfferSchedule().setId(offerCurrentlyInDB.getOfferSchedule().getId());
        offerToBeSavedInDB.getOfferLocation().setId(offerCurrentlyInDB.getOfferLocation().getId());
        offerToBeSavedInDB.getDateRange().setId(offerCurrentlyInDB.getDateRange().getId());
        offerToBeSavedInDB.getSalary().setId(offerCurrentlyInDB.getSalary().getId());
        offerToBeSavedInDB.setDateCreated(offerCurrentlyInDB.getDateCreated());
        offerToBeSavedInDB.setId(offerCurrentlyInDB.getId());
        return offerToBeSavedInDB;
    }
}

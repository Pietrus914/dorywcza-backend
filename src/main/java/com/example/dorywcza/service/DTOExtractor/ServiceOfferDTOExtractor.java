package com.example.dorywcza.service.DTOExtractor;


import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import com.example.dorywcza.service.IndustryService;
import com.example.dorywcza.service.SalaryTimeUnitService;
import com.example.dorywcza.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class ServiceOfferDTOExtractor extends OfferDTOExtractor {
    public ServiceOfferDTOExtractor(IndustryService industryService, SalaryTimeUnitService salaryTimeUnitService, UserService userService) {
        super(industryService, salaryTimeUnitService, userService);
    }

    public ServiceOffer getOffer(OfferPostDTO offerPostDTO){
        return new ServiceOffer(offerPostDTO.getTitle(), offerPostDTO.getDescription(),
                userService.convertFrom(userService.findById(offerPostDTO.getUserId()).get()), offerPostDTO.isHasExperience(),
                getOfferLocation(offerPostDTO), getDateRange(offerPostDTO),
                industryService.findById(offerPostDTO.getIndustryId()), getSalary(offerPostDTO), getOfferSchedule(offerPostDTO));
    }
}

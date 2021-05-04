package com.example.dorywcza.service.DTOExtractor;

import com.example.dorywcza.model.offer.*;
import com.example.dorywcza.model.offer.DTO.*;
import com.example.dorywcza.model.user.DTO.UserSimplifiedDTO;
import com.example.dorywcza.model.user.User;
import com.example.dorywcza.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferExtractor {

    private IndustryService industryService;

    @Autowired
    public OfferExtractor(IndustryService industryService) {
        this.industryService = industryService;
    }

    private SalaryDTO getSalaryDTO(Salary salary) {
        return new SalaryDTO(salary.getMinSalary(), salary.getMaxSalary());
    }

    private OfferLocationDTO getOfferLocationDTO(OfferLocation offerLocation) {
        return new OfferLocationDTO(offerLocation.getXPosition(), offerLocation.getYPosition(), offerLocation.getCityName());
    }

    private DateRangeDTO getDataRangeDTO(DateRange dateRange) {
        return new DateRangeDTO(dateRange.getStartDate(), dateRange.getEndDate());
    }

    private OfferScheduleDTO getOfferScheduleDTO(OfferSchedule offerSchedule) {
        return new OfferScheduleDTO(offerSchedule);
    }

    private SalaryTimeUnitDTO getSalaryTimeUnitDTO(SalaryTimeUnit salaryTimeUnit) {
        return new SalaryTimeUnitDTO(salaryTimeUnit.getId(), salaryTimeUnit.getName());
    }

    private IndustryDTO getIndustryDTO(Industry industry) {
        IndustryDTO industryDTO = new IndustryDTO(industry.getId(), industry.getName());

        Industry currentIndustry = industry;
        IndustryDTO currentIndustryDTO = industryDTO;

        while (currentIndustry.getParentId() != null){
            Industry parentIndustry = industryService.findById(currentIndustry.getParentId());
            IndustryDTO parentIndustryDTO = new IndustryDTO(parentIndustry.getId(), parentIndustry.getName());
            currentIndustryDTO.setParentIndustryDTO(parentIndustryDTO);

            currentIndustry = parentIndustry;
            currentIndustryDTO = parentIndustryDTO;
        }

        return industryDTO;
//        return new IndustryDTO(industry.getId(), industry.getName(), industry.getParentId());
    }

    private UserSimplifiedDTO getUserSimplifiedDTO(User user) {
        return new UserSimplifiedDTO(user);

    }

    public OfferPostDTO getOfferDTO(Offer offer, List<String> tagsNames) {
        return new OfferPostDTO(offer.getId(), offer.getDescription(), offer.getTitle(), getUserSimplifiedDTO(offer.getUser()),
                getSalaryTimeUnitDTO(offer.getSalary().getSalaryTimeUnit()), offer.isHasExperience(), getSalaryDTO(offer.getSalary()),
                getOfferLocationDTO(offer.getOfferLocation()), getDataRangeDTO(offer.getDateRange()),
                getIndustryDTO(offer.getIndustry()), getOfferScheduleDTO(offer.getOfferSchedule()), tagsNames, offer.getDateCreated(), offer.getDateUpdated());
    }
}

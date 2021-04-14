package com.example.dorywcza.service.DTOExtractor;

import com.example.dorywcza.model.offer.*;
import com.example.dorywcza.model.offer.DTO.*;
import org.springframework.stereotype.Service;

@Service
public class OfferExtractor {

    private SalaryDTO getSalaryDTO(Salary salary) {
        return new SalaryDTO(salary.getMinSalary(), salary.getMaxSalary());
    }

    private OfferLocationDTO getOfferLocationDTO(OfferLocation offerLocation) {
        return new OfferLocationDTO(offerLocation.getXPosition(), offerLocation.getYPosition());
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
        return new IndustryDTO(industry.getId(), industry.getName(), industry.getParentId());
    }

    public OfferPostDTO getOfferDTO(Offer offer) {
        return new OfferPostDTO(offer.getDescription(), offer.getTitle(), offer.getUser().getId(),
                getSalaryTimeUnitDTO(offer.getSalary().getSalaryTimeUnit()), offer.isHasExperience(), getSalaryDTO(offer.getSalary()),
                getOfferLocationDTO(offer.getOfferLocation()), getDataRangeDTO(offer.getDateRange()),
                getIndustryDTO(offer.getIndustry()), getOfferScheduleDTO(offer.getOfferSchedule()), offer.getDateCreated(), offer.getDateUpdated());
    }
}

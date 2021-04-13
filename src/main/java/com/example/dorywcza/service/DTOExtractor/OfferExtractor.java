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

    public OfferPostDTO getOfferDTO(Offer offer) {
        return new OfferPostDTO(offer.getDescription(), offer.getTitle(), offer.getUser().getId(),
                offer.getSalary().getSalaryTimeUnit().getId(), offer.isHasExperience(), getSalaryDTO(offer.getSalary()),
                getOfferLocationDTO(offer.getOfferLocation()), getDataRangeDTO(offer.getDateRange()),
                offer.getIndustry().getId(), getOfferScheduleDTO(offer.getOfferSchedule()));
    }
}

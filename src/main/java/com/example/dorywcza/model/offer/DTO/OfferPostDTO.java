package com.example.dorywcza.model.offer.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class OfferPostDTO  {

    private String description;
    private String title;
//    When login is implemented this will be removed and replaced in service with user id from session
    private Long userId;
    private Long salaryTimeUnitId;
    private boolean hasExperience;
    private SalaryDTO salaryDTO;
    private OfferLocationDTO offerLocationDTO;
    private DateRangeDTO dateRangeDTO;
    private Long IndustryId;
    private OfferScheduleDTO offerScheduleDTO;
}

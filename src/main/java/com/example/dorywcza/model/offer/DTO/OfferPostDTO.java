package com.example.dorywcza.model.offer.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferPostDTO  {

    private String description;
    private String title;
//    When login is implemented this will be removed and replaced in service with user id from session
    private Long userId;
    private SalaryTimeUnitDTO salaryTimeUnitDTO;
    private boolean hasExperience;
    private SalaryDTO salaryDTO;
    private OfferLocationDTO offerLocationDTO;
    private DateRangeDTO dateRangeDTO;
    private IndustryDTO industryDTO;
    private OfferScheduleDTO offerScheduleDTO;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "CET")
    private Date dateCreated;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "CET")
    private Date dateUpdated;

    public OfferPostDTO(String description, String title, Long userId, SalaryTimeUnitDTO salaryTimeUnitDTO,
                        boolean hasExperience, SalaryDTO salaryDTO, OfferLocationDTO offerLocationDTO,
                        DateRangeDTO dateRangeDTO, IndustryDTO industryDTO, OfferScheduleDTO offerScheduleDTO) {
        this.description = description;
        this.title = title;
        this.userId = userId;
        this.salaryTimeUnitDTO = salaryTimeUnitDTO;
        this.hasExperience = hasExperience;
        this.salaryDTO = salaryDTO;
        this.offerLocationDTO = offerLocationDTO;
        this.dateRangeDTO = dateRangeDTO;
        this.industryDTO = industryDTO;
        this.offerScheduleDTO = offerScheduleDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferPostDTO that = (OfferPostDTO) o;
        return hasExperience == that.hasExperience && Objects.equals(description, that.description) && Objects.equals(title, that.title) && Objects.equals(userId, that.userId) && Objects.equals(salaryTimeUnitDTO, that.salaryTimeUnitDTO) && Objects.equals(salaryDTO, that.salaryDTO) && Objects.equals(offerLocationDTO, that.offerLocationDTO) && Objects.equals(dateRangeDTO, that.dateRangeDTO) && Objects.equals(industryDTO, that.industryDTO) && Objects.equals(offerScheduleDTO, that.offerScheduleDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, title, userId, salaryTimeUnitDTO, hasExperience, salaryDTO, offerLocationDTO, dateRangeDTO, industryDTO, offerScheduleDTO);
    }
}

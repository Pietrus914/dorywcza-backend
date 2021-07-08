package com.example.dorywcza.model.offer.DTO;


import com.example.dorywcza.model.user.DTO.UserSimplifiedDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferPostDTO  {
    private Long id;
    private String description;
    private String title;
//    When login is implemented this will be removed and replaced in service with user id from session
    private UserSimplifiedDTO userSimplifiedDTO;
    private SalaryTimeUnitDTO salaryTimeUnitDTO;
    private boolean hasExperience;
    private SalaryDTO salaryDTO;
    private OfferLocationDTO offerLocationDTO;
    private DateRangeDTO dateRangeDTO;
    private IndustryDTO industryDTO;
    private OfferScheduleDTO offerScheduleDTO;
    private List<String> tagsNames;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "CET")
    private Date dateCreated;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "CET")
    private Date dateUpdated;

    public OfferPostDTO(String description, String title, UserSimplifiedDTO userSimplifiedDT, SalaryTimeUnitDTO salaryTimeUnitDTO,
                        boolean hasExperience, SalaryDTO salaryDTO, OfferLocationDTO offerLocationDTO,
                        DateRangeDTO dateRangeDTO, IndustryDTO industryDTO, OfferScheduleDTO offerScheduleDTO, List<String> tagsNames) {
        this.description = description;
        this.title = title;
        this.userSimplifiedDTO = userSimplifiedDT;
        this.salaryTimeUnitDTO = salaryTimeUnitDTO;
        this.hasExperience = hasExperience;
        this.salaryDTO = salaryDTO;
        this.offerLocationDTO = offerLocationDTO;
        this.dateRangeDTO = dateRangeDTO;
        this.industryDTO = industryDTO;
        this.offerScheduleDTO = offerScheduleDTO;
        this.tagsNames = tagsNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferPostDTO that = (OfferPostDTO) o;
        return hasExperience == that.hasExperience && Objects.equals(description, that.description) && Objects.equals(title, that.title) && Objects.equals(userSimplifiedDTO, that.userSimplifiedDTO) && Objects.equals(salaryTimeUnitDTO, that.salaryTimeUnitDTO) && Objects.equals(salaryDTO, that.salaryDTO) && Objects.equals(offerLocationDTO, that.offerLocationDTO) && Objects.equals(dateRangeDTO, that.dateRangeDTO) && Objects.equals(industryDTO, that.industryDTO) && Objects.equals(offerScheduleDTO, that.offerScheduleDTO) && Objects.equals(tagsNames, that.tagsNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, title, userSimplifiedDTO, salaryTimeUnitDTO, hasExperience, salaryDTO, offerLocationDTO, dateRangeDTO, industryDTO, offerScheduleDTO, tagsNames);
    }
}


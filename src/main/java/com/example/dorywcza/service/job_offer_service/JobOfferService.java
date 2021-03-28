package com.example.dorywcza.service.job_offer_service;


import com.example.dorywcza.model.Industry;
import com.example.dorywcza.model.SalaryTimeUnit;
import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.job_offer.JobOfferRequestWrapper;
import com.example.dorywcza.model.job_offer.JobSalary;
import com.example.dorywcza.repository.job_offer_repository.JobOfferRepository;
import com.example.dorywcza.service.IndustryService;
import com.example.dorywcza.service.SalaryTimeUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobOfferService {

    private final JobOfferRepository repository;
    private IndustryService industryService;
    private JobSalaryService jobSalaryService;
    private SalaryTimeUnitService salaryTimeUnitService;

    @Autowired
    public JobOfferService(JobOfferRepository repository, IndustryService industryService,
                           JobSalaryService jobSalaryService, SalaryTimeUnitService salaryTimeUnitService) {
        this.repository = repository;
        this.industryService =  industryService;
        this.jobSalaryService = jobSalaryService;
        this.salaryTimeUnitService = salaryTimeUnitService;

    }

    public List<JobOffer> findAll(){
        return repository.findAll();
    }

    public void saveJobOffer(JobOfferRequestWrapper jobOfferRequestWrapper){
        JobOffer jobOffer = jobOfferRequestWrapper.getJobOffer();
        Long industryId = jobOfferRequestWrapper.getIndustryId();
        Long salaryTimeUnitId = jobOfferRequestWrapper.getSalaryTimeUnitId();
        JobSalary jobSalary = jobOfferRequestWrapper.getJobSalary();
        SalaryTimeUnit salaryTimeUnit = salaryTimeUnitService.findSalaryTimeUnitById(salaryTimeUnitId);
        jobSalary.setSalaryTimeUnit(salaryTimeUnit);
        Industry industry = industryService.findByIdId(industryId).get();
        jobOffer.setIndustry(industry);
        jobOffer.setJobSalary(jobSalary);

        repository.save(jobOffer);
    }
}

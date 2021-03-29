package com.example.dorywcza.service.job_offer_service;


import com.example.dorywcza.model.SalaryTimeUnit;
import com.example.dorywcza.model.job_offer.JobSalary;
import com.example.dorywcza.repository.SalaryTimeUnitRepository;
import com.example.dorywcza.repository.job_offer_repository.JobSalaryRepository;
import com.example.dorywcza.service.SalaryTimeUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobSalaryService {

    private JobSalaryRepository jobSalaryRepository;
    private SalaryTimeUnitService salaryTimeUnitService;

    @Autowired
    public JobSalaryService(JobSalaryRepository jobSalaryRepository, SalaryTimeUnitService salaryTimeUnitService) {
        this.jobSalaryRepository = jobSalaryRepository;
        this.salaryTimeUnitService = salaryTimeUnitService;
    }

//    public JobSalary saveJobSalary(JobSalary jobSalary, Long salaryTimeUnitId){
//        SalaryTimeUnit salaryTimeUnit = salaryTimeUnitService.findSalaryTimeUnitById(salaryTimeUnitId);
//        jobSalary.setSalaryTimeUnit(salaryTimeUnit);
//        return jobSalaryRepository.save(jobSalary);
//    }

}

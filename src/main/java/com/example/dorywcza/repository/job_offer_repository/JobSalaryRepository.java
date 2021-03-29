package com.example.dorywcza.repository.job_offer_repository;

import com.example.dorywcza.model.job_offer.JobSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSalaryRepository extends JpaRepository <JobSalary, Long> {
}

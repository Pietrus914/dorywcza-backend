package com.example.dorywcza.repository;

import com.example.dorywcza.model.SalaryTimeUnit;
import com.example.dorywcza.model.job_offer.JobSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryTimeUnitRepository extends JpaRepository<SalaryTimeUnit, Long> {
}

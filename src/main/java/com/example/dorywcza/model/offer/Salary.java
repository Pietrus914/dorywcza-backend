package com.example.dorywcza.model.offer;


import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Salary {

    @Id
    @Column(name = "SALARY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long minSalary;
    private Long maxSalary;

    @ManyToOne
    @JoinColumn(name = "SALARY_TIME_UNIT_ID")
    private SalaryTimeUnit salaryTimeUnit;

    public Salary(Long minSalary, Long maxSalary) {
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salary salary = (Salary) o;
        return Objects.equals(minSalary, salary.minSalary) && Objects.equals(maxSalary, salary.maxSalary) && Objects.equals(salaryTimeUnit, salary.salaryTimeUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minSalary, maxSalary, salaryTimeUnit);
    }
}

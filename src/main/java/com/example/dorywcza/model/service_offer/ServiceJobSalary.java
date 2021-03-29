package com.example.dorywcza.model.service_offer;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity
public class ServiceJobSalary {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private int min;
    private int max;
    private String salaryUnit;

    public ServiceJobSalary(int min, int max, String salaryUnit) {
        this.min = min;
        this.max = max;
        this.salaryUnit = salaryUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceJobSalary that = (ServiceJobSalary) o;
        return min == that.min && max == that.max && Objects.equals(salaryUnit, that.salaryUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max, salaryUnit);
    }
}

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
public class ServiceLocation {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private double xPosition;
    private double yPosition;

    public ServiceLocation(double xPosition, double yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceLocation that = (ServiceLocation) o;
        return Double.compare(that.xPosition, xPosition) == 0 && Double.compare(that.yPosition, yPosition) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition);
    }
}

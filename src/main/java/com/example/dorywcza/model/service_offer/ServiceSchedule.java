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
public class ServiceSchedule {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private boolean mondayMorning;
    private boolean mondayAfternoon;
    private boolean mondayEvening;
    private boolean tuesdayMorning;
    private boolean tuesdayAfternoon;
    private boolean tuesdayEvening;
    private boolean wednesdayMorning;
    private boolean wednesdayAfternoon;
    private boolean wednesdayEvening;
    private boolean thursdayMorning;
    private boolean thursdayAfternoon;
    private boolean thursdayEvening;
    private boolean fridayMorning;
    private boolean fridayAfternoon;
    private boolean fridayEvening;
    private boolean saturdayMorning;
    private boolean saturdayAfternoon;
    private boolean saturdayEvening;
    private boolean sundayMorning;
    private boolean sundayAfternoon;
    private boolean sundayEvening;

    public ServiceSchedule(boolean mondayMorning, boolean mondayAfternoon, boolean mondayEvening, boolean tuesdayMorning, boolean tuesdayAfternoon, boolean tuesdayEvening, boolean wednesdayMorning, boolean wednesdayAfternoon, boolean wednesdayEvening, boolean thursdayMorning, boolean thursdayAfternoon, boolean thursdayEvening, boolean fridayMorning, boolean fridayAfternoon, boolean fridayEvening, boolean saturdayMorning, boolean saturdayAfternoon, boolean saturdayEvening, boolean sundayMorning, boolean sundayAfternoon, boolean sundayEvening) {
        this.mondayMorning = mondayMorning;
        this.mondayAfternoon = mondayAfternoon;
        this.mondayEvening = mondayEvening;
        this.tuesdayMorning = tuesdayMorning;
        this.tuesdayAfternoon = tuesdayAfternoon;
        this.tuesdayEvening = tuesdayEvening;
        this.wednesdayMorning = wednesdayMorning;
        this.wednesdayAfternoon = wednesdayAfternoon;
        this.wednesdayEvening = wednesdayEvening;
        this.thursdayMorning = thursdayMorning;
        this.thursdayAfternoon = thursdayAfternoon;
        this.thursdayEvening = thursdayEvening;
        this.fridayMorning = fridayMorning;
        this.fridayAfternoon = fridayAfternoon;
        this.fridayEvening = fridayEvening;
        this.saturdayMorning = saturdayMorning;
        this.saturdayAfternoon = saturdayAfternoon;
        this.saturdayEvening = saturdayEvening;
        this.sundayMorning = sundayMorning;
        this.sundayAfternoon = sundayAfternoon;
        this.sundayEvening = sundayEvening;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceSchedule that = (ServiceSchedule) o;
        return mondayMorning == that.mondayMorning && mondayAfternoon == that.mondayAfternoon && mondayEvening == that.mondayEvening && tuesdayMorning == that.tuesdayMorning && tuesdayAfternoon == that.tuesdayAfternoon && tuesdayEvening == that.tuesdayEvening && wednesdayMorning == that.wednesdayMorning && wednesdayAfternoon == that.wednesdayAfternoon && wednesdayEvening == that.wednesdayEvening && thursdayMorning == that.thursdayMorning && thursdayAfternoon == that.thursdayAfternoon && thursdayEvening == that.thursdayEvening && fridayMorning == that.fridayMorning && fridayAfternoon == that.fridayAfternoon && fridayEvening == that.fridayEvening && saturdayMorning == that.saturdayMorning && saturdayAfternoon == that.saturdayAfternoon && saturdayEvening == that.saturdayEvening && sundayMorning == that.sundayMorning && sundayAfternoon == that.sundayAfternoon && sundayEvening == that.sundayEvening;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mondayMorning, mondayAfternoon, mondayEvening, tuesdayMorning, tuesdayAfternoon, tuesdayEvening, wednesdayMorning, wednesdayAfternoon, wednesdayEvening, thursdayMorning, thursdayAfternoon, thursdayEvening, fridayMorning, fridayAfternoon, fridayEvening, saturdayMorning, saturdayAfternoon, saturdayEvening, sundayMorning, sundayAfternoon, sundayEvening);
    }
}

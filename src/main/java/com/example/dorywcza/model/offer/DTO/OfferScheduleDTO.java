package com.example.dorywcza.model.offer.DTO;

import com.example.dorywcza.model.offer.OfferSchedule;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OfferScheduleDTO {
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

    public OfferScheduleDTO(OfferSchedule offerSchedule) {
        this.mondayMorning = offerSchedule.isMondayMorning();
        this.mondayAfternoon = offerSchedule.isMondayAfternoon();
        this.mondayEvening = offerSchedule.isMondayEvening();
        this.tuesdayMorning = offerSchedule.isTuesdayMorning();
        this.tuesdayAfternoon = offerSchedule.isTuesdayAfternoon();
        this.tuesdayEvening = offerSchedule.isTuesdayEvening();
        this.wednesdayMorning = offerSchedule.isWednesdayMorning();
        this.wednesdayAfternoon = offerSchedule.isWednesdayAfternoon();
        this.wednesdayEvening = offerSchedule.isWednesdayEvening();
        this.thursdayMorning = offerSchedule.isThursdayMorning();
        this.thursdayAfternoon = offerSchedule.isThursdayAfternoon();
        this.thursdayEvening = offerSchedule.isThursdayEvening();
        this.fridayMorning = offerSchedule.isFridayMorning();
        this.fridayAfternoon = offerSchedule.isFridayAfternoon();
        this.fridayEvening = offerSchedule.isFridayEvening();
        this.saturdayMorning = offerSchedule.isSaturdayMorning();
        this.saturdayAfternoon = offerSchedule.isSaturdayAfternoon();
        this.saturdayEvening = offerSchedule.isSaturdayEvening();
        this.sundayMorning = offerSchedule.isSundayMorning();
        this.sundayAfternoon = offerSchedule.isSundayAfternoon();
        this.sundayEvening = offerSchedule.isSundayEvening();
    }
}

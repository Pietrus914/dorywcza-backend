package com.example.dorywcza.controller;

import com.example.dorywcza.model.service_offer.*;
import com.example.dorywcza.service.ServiceLocationService;
import com.example.dorywcza.service.ServiceOfferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class ServiceOfferControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ServiceLocationService serviceLocationService;

    @Autowired
    private ServiceOfferService serviceOfferService;

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void getAddServiceOffer() {
    }

    @Test
    @DirtiesContext
    void addServiceOffer_correctServiceOffer_returnAddOffer() throws Exception {
        ServiceDateRange serviceDateRangeToSave = new ServiceDateRange(Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()));
        ServiceJobSalary serviceJobSalaryToSave = new ServiceJobSalary(1000, 1001, "week");
        ServiceLocation serviceLocationToSave = new ServiceLocation(0.1, 0.1);
        ServiceSchedule serviceScheduleToSave = new ServiceSchedule(false, false,
                false, false, false, false, false,
                false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false);

        ServiceOffer serviceOfferToSave = new ServiceOffer(1, "test1", "test1", serviceDateRangeToSave,
                true, serviceJobSalaryToSave, serviceLocationToSave, serviceScheduleToSave);

        var serviceOfferInJson = objectMapper.writeValueAsString(serviceOfferToSave);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/add-service-offer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serviceOfferInJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        ServiceOffer serviceOfferFromDb = objectMapper.readValue(contentAsString, ServiceOffer.class);

        List<ServiceLocation> serviceLocationList = serviceLocationService.findAll();
        System.out.println();

        assertAll(
                () -> assertEquals(serviceOfferToSave, serviceOfferFromDb),
                () -> assertEquals(serviceDateRangeToSave.getEndDate().toString(), serviceOfferFromDb.getServiceDateRange().getEndDate().toString()),
                () -> assertEquals(serviceDateRangeToSave.getStartDate().toString(), serviceOfferFromDb.getServiceDateRange().getStartDate().toString()),
                () -> assertEquals(serviceJobSalaryToSave, serviceOfferFromDb.getServiceJobSalary()),
                () -> assertEquals(serviceLocationToSave, serviceOfferFromDb.getServiceLocation()),
                () -> assertEquals(serviceScheduleToSave, serviceOfferFromDb.getServiceSchedule())
        );

    }

    @Test
    @DirtiesContext
    void updateServiceOffer_correctServiceOffer_returnUpdateOffer() throws Exception {
        ServiceDateRange serviceDateRangeToSave = new ServiceDateRange(Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()));
        ServiceJobSalary serviceJobSalaryToSave = new ServiceJobSalary(1000, 1001, "week");
        ServiceLocation serviceLocationToSave = new ServiceLocation(0.1, 0.1);
        ServiceSchedule serviceScheduleToSave = new ServiceSchedule(false, false,
                false, false, false, false, false,
                false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false);

        ServiceOffer serviceOfferToSave = new ServiceOffer(1, "test1", "test1", serviceDateRangeToSave,
                true, serviceJobSalaryToSave, serviceLocationToSave, serviceScheduleToSave);

        var serviceOfferInJson = objectMapper.writeValueAsString(serviceOfferToSave);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/update-service-offer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serviceOfferInJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        ServiceOffer serviceOfferFromDb = objectMapper.readValue(contentAsString, ServiceOffer.class);


        assertAll(
                () -> assertEquals(serviceOfferToSave, serviceOfferFromDb),
                () -> assertEquals(serviceDateRangeToSave.getEndDate().toString(), serviceOfferFromDb.getServiceDateRange().getEndDate().toString()),
                () -> assertEquals(serviceDateRangeToSave.getStartDate().toString(), serviceOfferFromDb.getServiceDateRange().getStartDate().toString()),
                () -> assertEquals(serviceJobSalaryToSave, serviceOfferFromDb.getServiceJobSalary()),
                () -> assertEquals(serviceLocationToSave, serviceOfferFromDb.getServiceLocation()),
                () -> assertEquals(serviceScheduleToSave, serviceOfferFromDb.getServiceSchedule())
        );

    }

    @Test
    @DirtiesContext
    void deleteServiceOffer_correctServiceOffer_returnDeleteOffer() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/service-offers/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        List<ServiceOffer> serviceOfferList = serviceOfferService.findAll();
        List<ServiceLocation> serviceLocationList = serviceLocationService.findAll();
        System.out.println(serviceLocationList);
        System.out.println(serviceOfferList.size());
    }
}
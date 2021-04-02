package com.example.dorywcza.controller;

import com.example.dorywcza.model.offer.*;
import com.example.dorywcza.model.service_offer.*;
import com.example.dorywcza.service.IndustryService;
import com.example.dorywcza.service.SalaryTimeUnitService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
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
    private SalaryTimeUnitService salaryTimeUnitService;

    @Autowired
    private IndustryService industryService;

    @Test
    @DirtiesContext
    @DisplayName("Get http://localhost:8080/service-offers -> http status 200, get 2 test service offer" )
    void findAll() throws Exception {
        int expectedSize = 2;

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/service-offers"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        List<ServiceOffer> serviceOfferList = objectMapper.readValue(contentAsString, new TypeReference<>() {});

        assertEquals(expectedSize, serviceOfferList.size());
    }

    @Test
    @DirtiesContext
    @DisplayName("Post http://localhost:8080/service-offers/1 -> http status 200, get first service offer with id 1" )
    void findById() throws Exception {
        Salary expectedSalary = new Salary(101L, 201L);
        expectedSalary.setSalaryTimeUnit(salaryTimeUnitService.findSalaryTimeUnitById(1L));
        DateRange expectedDateRange = new DateRange(Date.valueOf("2012-09-17"), Date.valueOf("2020-09-17"));
        OfferLocation expectedOfferLocation = new OfferLocation(0.1, 0.1);
        OfferSchedule expectedOfferSchedule = new OfferSchedule(true, false, true,
                true, true, true, true, true,
                true, true, true, true, true,
                true, true, true, true, true,
                true, true, true);
        Industry expectedIndustry = industryService.findById(1L);
        Date expectedDateCreate = Date.valueOf("2021-04-01");
        Date expectedDateUpdated = Date.valueOf("2021-04-02");
        String expectedDescription = "test SERVIC OFFER 1";
        String expectedTitle = "test SERVIC OFFER 1";
        Long expectedId = 1L;

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/service-offers/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        ServiceOffer serviceOffer = objectMapper.readValue(contentAsString, ServiceOffer.class);
        System.out.println(serviceOffer);

        assertAll(
                () -> assertEquals(expectedDateRange, serviceOffer.getDateRange()),
                () -> assertEquals(expectedOfferLocation, serviceOffer.getOfferLocation()),
                () -> assertEquals(expectedOfferSchedule, serviceOffer.getOfferSchedule()),
                () -> assertEquals(expectedSalary, serviceOffer.getSalary()),
                () -> assertEquals(expectedIndustry, serviceOffer.getIndustry()),
                () -> assertFalse(serviceOffer.isHasExperience()),
                () -> assertEquals(expectedDateCreate, serviceOffer.getDateCreated()),
                () -> assertEquals(expectedDateUpdated, serviceOffer.getDateUpdated()),
                () -> assertEquals(expectedDescription, serviceOffer.getDescription()),
                () -> assertEquals(expectedTitle, serviceOffer.getTitle()),
                () -> assertEquals(expectedId, serviceOffer.getId())
        );
    }

    @Test
    void getAddServiceOffer() {
    }

//    @Test
//    @DirtiesContext
//    void addServiceOffer_correctServiceOffer_returnAddOffer() throws Exception {
//        DateRange dateRangeToSave = new DateRange(Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()));
//        Salary jobSalaryToSave = new Salary();
//        OfferLocation offerLocationToSave = new OfferLocation(0.1, 0.1);
//        OfferSchedule offerScheduleToSave = new OfferSchedule(false, false,
//                false, false, false, false, false,
//                false, false, false, false,
//                false, false, false, false, false,
//                false, false, false, false, false);
//
//        ServiceOffer serviceOfferToSave = new ServiceOffer(1, "test1", "test1", dateRangeToSave,
//                true, jobSalaryToSave, offerLocationToSave, offerScheduleToSave);
//
//        var serviceOfferInJson = objectMapper.writeValueAsString(serviceOfferToSave);
//
//        System.out.println(serviceOfferInJson);
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
//                .post("/add-service-offer")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(serviceOfferInJson))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        String contentAsString = mvcResult.getResponse().getContentAsString();
//        ServiceOffer serviceOfferFromDb = objectMapper.readValue(contentAsString, ServiceOffer.class);
//
//        List<OfferLocation> offerLocationList = serviceLocationService.findAll();
//        System.out.println();
//
//        assertAll(
//                () -> assertEquals(serviceOfferToSave, serviceOfferFromDb),
//                () -> assertEquals(dateRangeToSave.getEndDate().toString(), serviceOfferFromDb.getDateRange().getEndDate().toString()),
//                () -> assertEquals(dateRangeToSave.getStartDate().toString(), serviceOfferFromDb.getDateRange().getStartDate().toString()),
//                () -> assertEquals(jobSalaryToSave, serviceOfferFromDb.getJobSalary()),
//                () -> assertEquals(offerLocationToSave, serviceOfferFromDb.getOfferLocation()),
//                () -> assertEquals(offerScheduleToSave, serviceOfferFromDb.getOfferSchedule())
//        );
//
//    }
//
//    @Test
//    @DirtiesContext
//    void updateServiceOffer_correctServiceOffer_returnUpdateOffer() throws Exception {
//        DateRange dateRangeToSave = new DateRange(Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()));
//        Salary jobSalaryToSave = new JobSalary(1000, 1001, "week");
//        OfferLocation offerLocationToSave = new OfferLocation(0.1, 0.1);
//        OfferSchedule offerScheduleToSave = new OfferSchedule(false, false,
//                false, false, false, false, false,
//                false, false, false, false,
//                false, false, false, false, false,
//                false, false, false, false, false);
//
//        ServiceOffer serviceOfferToSave = new ServiceOffer(1, "test1", "test1", dateRangeToSave,
//                true, jobSalaryToSave, offerLocationToSave, offerScheduleToSave);
//
//        var serviceOfferInJson = objectMapper.writeValueAsString(serviceOfferToSave);
//
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
//                .post("/update-service-offer/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(serviceOfferInJson))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        String contentAsString = mvcResult.getResponse().getContentAsString();
//        ServiceOffer serviceOfferFromDb = objectMapper.readValue(contentAsString, ServiceOffer.class);
//
//
//        assertAll(
//                () -> assertEquals(serviceOfferToSave, serviceOfferFromDb),
//                () -> assertEquals(dateRangeToSave.getEndDate().toString(), serviceOfferFromDb.getDateRange().getEndDate().toString()),
//                () -> assertEquals(dateRangeToSave.getStartDate().toString(), serviceOfferFromDb.getDateRange().getStartDate().toString()),
//                () -> assertEquals(jobSalaryToSave, serviceOfferFromDb.getJobSalary()),
//                () -> assertEquals(offerLocationToSave, serviceOfferFromDb.getOfferLocation()),
//                () -> assertEquals(offerScheduleToSave, serviceOfferFromDb.getOfferSchedule())
//        );
//
//    }
//
//    @Test
//    @DirtiesContext
//    void deleteServiceOffer_correctServiceOffer_returnDeleteOffer() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .delete("/service-offers/1"))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        List<ServiceOffer> serviceOfferList = serviceOfferService.findAll();
//        List<OfferLocation> offerLocationList = serviceLocationService.findAll();
//        System.out.println(offerLocationList);
//        System.out.println(serviceOfferList.size());
//    }
}
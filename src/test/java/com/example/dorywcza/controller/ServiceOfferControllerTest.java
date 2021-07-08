//package com.example.dorywcza.controller;
//
//import com.example.dorywcza.model.offer.DateRange;
//import com.example.dorywcza.model.offer.Salary;
//import com.example.dorywcza.model.offer.OfferLocation;
//import com.example.dorywcza.model.offer.OfferSchedule;
//import com.example.dorywcza.model.service_offer.*;
//import com.example.dorywcza.service.ServiceLocationService;
//import com.example.dorywcza.service.ServiceOfferService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class ServiceOfferControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private ServiceLocationService serviceLocationService;
//
//    @Autowired
//    private ServiceOfferService serviceOfferService;
//
//    @Test
//    void findAll() {
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void getAddServiceOffer() {
//    }
//
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
//}
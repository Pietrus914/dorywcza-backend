package com.example.dorywcza.controller;

import com.example.dorywcza.model.OfferType;
import com.example.dorywcza.model.offer.*;
import com.example.dorywcza.model.offer.DTO.*;
import com.example.dorywcza.model.service_offer.*;
import com.example.dorywcza.service.DTOExtractor.OfferDTOExtractor;
import com.example.dorywcza.service.IndustryService;
import com.example.dorywcza.service.SalaryTimeUnitService;
import com.example.dorywcza.service.ServiceOfferService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
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
    private SalaryTimeUnitService salaryTimeUnitService;

    @Autowired
    private IndustryService industryService;

    @Autowired
    private ServiceOfferService serviceOfferService;

    @Autowired
    private OfferDTOExtractor offerDTOExtractor;

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
    @DisplayName("Get http://localhost:8080/service-offers/1 -> http status 200, get first service offer with id 1" )
    void findById() throws Exception {
        Salary expectedSalary = new Salary(300L, 30L);
        expectedSalary.setSalaryTimeUnit(salaryTimeUnitService.findSalaryTimeUnitById(1L));
        DateRange expectedDateRange = new DateRange(Date.valueOf("2012-03-03"), Date.valueOf("2020-03-03"));
        OfferLocation expectedOfferLocation = new OfferLocation(0.1, 0.1);
        OfferSchedule expectedOfferSchedule = new OfferSchedule(true, false, true,
                true, true, true, true, true,
                true, true, true, true, true,
                true, true, true, true, true,
                true, true, true);
        Industry expectedIndustry = industryService.findById(1L);
        Date expectedDateCreate = Date.valueOf("2021-04-01");
        Date expectedDateUpdated = Date.valueOf("2021-04-02");
        String expectedDescription = "test SERVICE OFFER 1";
        String expectedTitle = "test SERVICE OFFER 1";
        Long expectedId = 1L;

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/service-offers/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        ServiceOffer serviceOffer = objectMapper.readValue(contentAsString, ServiceOffer.class);

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
    @DirtiesContext
    @DisplayName("Get http://localhost:8080/service-offers/10 -> http status 200, offer doesn't exist, return null" )
    void findById_shouldReturnNull() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/service-offers/10"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        ServiceOffer serviceOffer = objectMapper.readValue(contentAsString, ServiceOffer.class);

        assertNull(serviceOffer);
    }


    @Test
    @DirtiesContext
    @DisplayName("Post http://localhost:8080/add-service-offer -> http status 200, return Added offer" )
    void addServiceOffer_correctServiceOffer_returnAddOffer() throws Exception {
        DateRangeDTO dateRangeToSave = new DateRangeDTO(Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()));
        SalaryDTO jobSalaryToSave = new SalaryDTO(100L, 120L);
        OfferLocationDTO offerLocationToSave = new OfferLocationDTO(0.1, 0.1);
        OfferScheduleDTO offerScheduleToSave = new OfferScheduleDTO(false, false,
                false, false, false, false, false,
                false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false);

        OfferPostDTO serviceOfferToSave = new OfferPostDTO("test", "test", 1L, 1L,
                true, jobSalaryToSave, offerLocationToSave, dateRangeToSave, 1L, offerScheduleToSave);

        var serviceOfferInJson = objectMapper.writeValueAsString(serviceOfferToSave);

        ServiceOffer expectedServiceOffer = (ServiceOffer) offerDTOExtractor.getOfferV1(serviceOfferToSave, OfferType.SERVICE_OFFER);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/add-service-offer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serviceOfferInJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        ServiceOffer serviceOfferFromDb = objectMapper.readValue(contentAsString, ServiceOffer.class);

        assertEquals(expectedServiceOffer, serviceOfferFromDb);
    }

    @Test
    @DirtiesContext
    @DisplayName("Put http://localhost:8080/update-service-offer/1 -> http status 200, update and return offer with id 1" )
    void updateServiceOffer_correctServiceOffer_returnUpdateOffer() throws Exception {
        DateRangeDTO dateRangeToSave = new DateRangeDTO(Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()));
        SalaryDTO jobSalaryToSave = new SalaryDTO(100L, 120L);
        OfferLocationDTO offerLocationToSave = new OfferLocationDTO(0.1, 0.1);
        OfferScheduleDTO offerScheduleToSave = new OfferScheduleDTO(false, false,
                false, false, false, false, false,
                false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false);

        OfferPostDTO serviceOfferToSave = new OfferPostDTO("test", "test", 1L, 1L,
                true, jobSalaryToSave, offerLocationToSave, dateRangeToSave, 1L, offerScheduleToSave);

        var serviceOfferInJson = objectMapper.writeValueAsString(serviceOfferToSave);

        ServiceOffer expectedServiceOffer = (ServiceOffer) offerDTOExtractor.getOfferV1(serviceOfferToSave, OfferType.SERVICE_OFFER);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .put("/update-service-offer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serviceOfferInJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        ServiceOffer serviceOfferFromDb = objectMapper.readValue(contentAsString, ServiceOffer.class);

        assertEquals(expectedServiceOffer, serviceOfferFromDb);
    }

    @Test
    @DirtiesContext
    @DisplayName("Delete http://localhost:8080/service-offers/1 -> http status 200, delete offer with id 1" )
    void deleteServiceOffer_correctServiceOffer_returnDeleteOffer() throws Exception {
        int expectedSize = 1;

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/service-offers/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        List<OfferPostDTO> serviceOfferList = serviceOfferService.findAll();

        assertEquals(expectedSize, serviceOfferList.size());
    }
}
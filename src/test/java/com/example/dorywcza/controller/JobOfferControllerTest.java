package com.example.dorywcza.controller;

import com.example.dorywcza.model.DateRange;
import com.example.dorywcza.model.Industry;
import com.example.dorywcza.model.SalaryTimeUnit;
import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.job_offer.JobSalary;
import com.example.dorywcza.service.IndustryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;


        import com.fasterxml.jackson.core.type.TypeReference;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.http.MediaType;
        import org.springframework.mock.web.MockHttpServletRequest;
        import org.springframework.security.test.context.support.WithMockUser;
        import org.springframework.security.web.csrf.CsrfToken;
        import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
        import org.springframework.test.annotation.DirtiesContext;
        import org.springframework.test.web.servlet.MockMvc;
        import org.springframework.test.web.servlet.MvcResult;
        import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

        import static org.junit.jupiter.api.Assertions.assertAll;
        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
        import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class JobOfferControllerTest {


    private String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
    private HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
    private CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

    private Long testJobOfferId = Long.valueOf(1);
    private Date testStartDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-01");
    private Date testEndDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-31");
    private Date testDateCreated = new SimpleDateFormat("yyyy-MM-dd").parse("2021-04-01");
    private Date testDateUpdated = new SimpleDateFormat("yyyy-MM-dd").parse("2021-04-02");
    private Long testIndustryId  = Long.valueOf(1);
    private String testIndustryName = "Edukacja";
    private Long testJobSalaryId = Long.valueOf(1);
    private Long testMinSalary = Long.valueOf(10);
    private Long testMaxSalary = Long.valueOf(20);
    private Long testSalaryTimeUnitId = Long.valueOf(1);
    private String testSalaryTimeUnitName = "per hour";
    private String testJobOfferTitle = "test_title";
    private String testJobOfferDescription = "test_description";


    private Industry createTestIndustry(){
        Industry testIndustry = new Industry();
        testIndustry.setId(testIndustryId);
        testIndustry.setName(testIndustryName);
        return testIndustry;
    }

    private SalaryTimeUnit createTestSalaryTineUnit(){
        SalaryTimeUnit testSalaryTimeUnit = new SalaryTimeUnit();
        testSalaryTimeUnit.setId(testSalaryTimeUnitId);
        testSalaryTimeUnit.setName(testSalaryTimeUnitName);
        return testSalaryTimeUnit;
    }

    private JobSalary createTestJobSalary(){
        JobSalary testJobSalary = new JobSalary();
        testJobSalary.setId(testJobSalaryId);
        testJobSalary.setMinSalary(testMinSalary);
        testJobSalary.setMaxSalary(testMaxSalary);
        testJobSalary.setSalaryTimeUnit(createTestSalaryTineUnit());
        return testJobSalary;
    }


    private JobOffer createTestJobOffer(){
        JobOffer testJobOffer = new JobOffer();
        testJobOffer.setTitle(testJobOfferTitle);
        testJobOffer.setDescription(testJobOfferDescription);
        testJobOffer.setId(testJobOfferId);
        testJobOffer.setIndustry(createTestIndustry());
        testJobOffer.setJobSalary(createTestJobSalary());
        testJobOffer.setStartDate(testStartDate);
        testJobOffer.setEndDate(testEndDate);
        testJobOffer.setDateCreated(testDateCreated);
        testJobOffer.setDateUpdated(testDateUpdated);
        return testJobOffer;
    }


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    JobOfferControllerTest() throws ParseException {
    }


    @Test
    @WithMockUser("spring")
    @DirtiesContext
    @DisplayName("Get http://localhost:8080/jobs -> http status 200" )
    void getJobOffers() throws Exception {

        //        given db with one job offer
        JobOffer testJobOffer = createTestJobOffer();

        //        when
        MvcResult mvcResult = this.mockMvc.perform(get("/jobs"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        List<JobOffer> jobOffers = objectMapper.readValue(contentAsString, new TypeReference<List<JobOffer>>() {});

        //        then
        assertAll(
                () -> assertEquals(1, jobOffers.size()),
                () -> assertEquals(testJobOffer, jobOffers.get(0))
        );
    }

//
//    @Test
//    @WithMockUser("spring")
//    @DirtiesContext
////    @DisplayName("Post http://localhost:8080/hobbits -> http status 200, Peregin Took" )
//    void givenDbWithHobbitWhenPostRequestPathHobbitsThenSaveFrodoBaggins() throws Exception {
//
//
//
////      given hobbit in JSON
//        Hobbit hobbit = new Hobbit("Peregin", "Took");
//        var hobbitInJson = objectMapper.writeValueAsString(hobbit);
//
////      when hobbit post
//        MvcResult mvcResult = this.mockMvc
//                .perform(post("/hobbits")
//                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
//                        .param(csrfToken.getParameterName(), csrfToken.getToken())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(hobbitInJson))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
////      then hobbit get(1) returns hobbit
//        String contentAsString = mvcResult.getResponse().getContentAsString();
//        Hobbit savedHobbit = objectMapper.readValue(contentAsString, Hobbit.class);
//        assertAll(
//                () -> assertEquals("Peregin", savedHobbit.getName()),
//                () -> assertEquals("Took", savedHobbit.getLastName())
//        );
//    }
//
//    @Test
//    @WithMockUser("spring")
//    @DirtiesContext
//    void saveSauronFirstNameShouldThrowSauronExceptionError422() throws Exception {
////      given hobbit in JSON
//        Hobbit hobbit = new Hobbit("Sauron", "The Wizard");
//        var hobbitInJson = objectMapper.writeValueAsString(hobbit);
////      when hobbit post
//        this.mockMvc
//                .perform(post("/hobbits")
//                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
//                        .param(csrfToken.getParameterName(), csrfToken.getToken())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(hobbitInJson))
//                .andDo(print())
////      then throws 422 error
//                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
//
//    }
//
//    @Test
//    @WithMockUser("spring")
//    @DirtiesContext
//    void saveSauronLastNameShouldThrowSauronExceptionError422() throws Exception {
////      given hobbit in JSON
//        Hobbit hobbit = new Hobbit("TheWizard", "Sauron");
//        var hobbitInJson = objectMapper.writeValueAsString(hobbit);
////      when hobbit post
//        this.mockMvc
//                .perform(post("/hobbits")
//                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
//                        .param(csrfToken.getParameterName(), csrfToken.getToken())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(hobbitInJson))
//                .andDo(print())
////      then throws 422 error
//                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());

    }
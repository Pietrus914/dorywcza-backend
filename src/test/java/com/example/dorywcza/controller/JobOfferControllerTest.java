package com.example.dorywcza.controller;

import com.example.dorywcza.model.Industry;
import com.example.dorywcza.model.SalaryTimeUnit;
import com.example.dorywcza.model.job_offer.JobOfferRequestWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.job_offer.JobSalary;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;


import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.text.ParseException;
import java.util.TimeZone;

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


//    Data to test getJobOffer method
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

//    Data to test postJobOffer method
    private Long postedJobOfferId = Long.valueOf(2);
    private Date postedStartDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-02");
    private Date postedEndDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-30");
    private String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    private Long postedIndustryId  = Long.valueOf(2);
    private String postedIndustryName = "Gastronomia & Eventy";
    private Long postedJobSalaryId = Long.valueOf(2);
    private Long postedMinSalary = Long.valueOf(100);
    private Long postedMaxSalary = Long.valueOf(200);
    private Long postedSalaryTimeUnitId = Long.valueOf(2);
    private String postedSalaryTimeUnitName = "per project";
    private String postedJobOfferTitle = "posted_title";
    private String postedJobOfferDescription = "posted_description";



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    JobOfferControllerTest() throws ParseException {
    }


    @Test
    @WithMockUser("spring")
    @DirtiesContext
    @DisplayName("Get http://localhost:8080/jobs -> http status 200, get testJobOffer" )
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


    @Test
    @WithMockUser("spring")
    @DirtiesContext
//    @DisplayName("Post http://localhost:8080/jobs -> http status 200, postedJobOfferRequest" )
    void postJobOffer() throws Exception {

//      given hobbit in JSON

        var jobOfferRequestWrapper = objectMapper.writeValueAsString(createJobOfferPostRequestWrapper());
//      when posting job offer details
        MvcResult mvcResult = this.mockMvc
                .perform(post("/jobs")
                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                        .param(csrfToken.getParameterName(), csrfToken.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jobOfferRequestWrapper))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
//      then returns postedJobOffer
        String contentAsString = mvcResult.getResponse().getContentAsString();
        JobOffer postedJobOffer = objectMapper.readValue(contentAsString, JobOffer.class);
        JobOffer savedJobOffer =  createSavedJobOffer();
        assertAll(
                () -> assertEquals(postedJobOffer, savedJobOffer));
    }

//    Helper methods to create objects that are compared in test results

    private Industry createTestIndustry(Long industryId, String industryName){
        Industry testIndustry = new Industry();
        testIndustry.setId(industryId);
        testIndustry.setName(industryName);
        return testIndustry;
    }

    private SalaryTimeUnit createTestSalaryTimeUnit(){
        SalaryTimeUnit testSalaryTimeUnit = new SalaryTimeUnit();
        testSalaryTimeUnit.setId(testSalaryTimeUnitId);
        testSalaryTimeUnit.setName(testSalaryTimeUnitName);
        return testSalaryTimeUnit;
    }

    private JobSalary createTestJobSalary(Long jobSalaryId, Long minSalary, Long maxSalary){
        JobSalary testJobSalary = new JobSalary();
        testJobSalary.setId(jobSalaryId);
        testJobSalary.setMinSalary(minSalary);
        testJobSalary.setMaxSalary(maxSalary);
        testJobSalary.setSalaryTimeUnit(createTestSalaryTimeUnit());
        return testJobSalary;
    }


    private JobOffer createTestJobOffer(){
        JobOffer testJobOffer = new JobOffer();
        testJobOffer.setTitle(testJobOfferTitle);
        testJobOffer.setDescription(testJobOfferDescription);
        testJobOffer.setId(testJobOfferId);
        testJobOffer.setIndustry(createTestIndustry(testIndustryId, testIndustryName));
        testJobOffer.setJobSalary(createTestJobSalary(testJobSalaryId, testMinSalary, testMaxSalary));
        testJobOffer.setStartDate(testStartDate);
        testJobOffer.setEndDate(testEndDate);
        testJobOffer.setDateCreated(testDateCreated);
        testJobOffer.setDateUpdated(testDateUpdated);
        return testJobOffer;
    }


//    Create instance of JobOffer that is expected to be created after post method
private JobOffer createSavedJobOffer () throws ParseException {
    Industry postedIndustry = createTestIndustry(postedIndustryId, postedIndustryName);

    SalaryTimeUnit postedSalaryTimeUnit = new SalaryTimeUnit();
    postedSalaryTimeUnit.setId(postedSalaryTimeUnitId);
    postedSalaryTimeUnit.setName(postedSalaryTimeUnitName);

    JobSalary postedJobSalary = new JobSalary();
    postedJobSalary.setId(postedJobSalaryId);
    postedJobSalary.setMinSalary(postedMinSalary);
    postedJobSalary.setMaxSalary(postedMaxSalary);
    postedJobSalary.setSalaryTimeUnit(postedSalaryTimeUnit);

    JobOffer savedJobOffer = createJobOfferForPost();
    savedJobOffer.setJobSalary(postedJobSalary);
    savedJobOffer.setIndustry(postedIndustry);
    savedJobOffer.setId(postedJobOfferId);

    savedJobOffer.setDateCreated(getCurrentDateFormatted());
    return savedJobOffer;
}


private Date getCurrentDateFormatted() throws ParseException {
    SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
    isoFormat.setTimeZone(TimeZone.getTimeZone("CET"));
    Date date = isoFormat.parse(currentDate);
    return date;
}

private JobOffer createJobOfferForPost(){
    JobOffer postedJobOffer = new JobOffer();
    postedJobOffer.setTitle(postedJobOfferTitle);
    postedJobOffer.setDescription(postedJobOfferDescription);
    postedJobOffer.setStartDate(postedStartDate);
    postedJobOffer.setEndDate(postedEndDate);
    return postedJobOffer;
}

private JobOfferRequestWrapper createJobOfferPostRequestWrapper(){
        JobOffer postedJobOffer = createJobOfferForPost();
        JobSalary postedJobSalary = new JobSalary();
        postedJobSalary.setMinSalary(postedMinSalary);
        postedJobSalary.setMaxSalary(postedMaxSalary);
        JobOfferRequestWrapper jobOfferRequestWrapper = new JobOfferRequestWrapper(postedJobOffer, postedIndustryId,
                postedSalaryTimeUnitId, postedJobSalary);
        return jobOfferRequestWrapper;
}

}
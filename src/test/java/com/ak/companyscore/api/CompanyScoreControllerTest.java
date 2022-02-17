package com.ak.companyscore.api;


import com.ak.companyscore.dto.CompanyScoreRequest;
import com.ak.companyscore.dto.CompanyScoreResponse;
import com.ak.companyscore.mongo.dao.CompanyScoreDao;
import com.ak.companyscore.service.CompanyScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyScoreControllerTest {
    @Mock
    private CompanyScoreService companyScoreService;

    private CompanyScoreController CompanyScoreOutput;

    @BeforeEach
    void setUp() {
        CompanyScoreOutput = new CompanyScoreController(companyScoreService);
    }

    @Test
    public void saveCompanyScore_returnFromService() {
        ZonedDateTime lastUpdated = ZonedDateTime.now(UTC);
        ZonedDateTime registrationDate = ZonedDateTime.now(UTC);
        var companyScoreRequest = CompanyScoreRequest.builder()
                .msgId(BigInteger.ONE)
                .companyName("test")
                .directorsCount(1)
                .lastUpdated(lastUpdated)
                .registrationDate(registrationDate)
                .build();

        var companyScoreDao = CompanyScoreDao.builder()
                .msgId(BigInteger.ONE)
                .companyName("test")
                .directorsCount(1)
                .lastUpdated(lastUpdated)
                .registrationDate(registrationDate)
                .build();

        when(companyScoreService.saveCompanyScore(
                companyScoreRequest.getMsgId(),
                companyScoreRequest.getCompanyName(),
                companyScoreRequest.getRegistrationDate(),
                companyScoreRequest.getDirectorsCount(),
                companyScoreRequest.getScore(),
                companyScoreRequest.getLastUpdated())
        ).thenReturn(companyScoreDao);

        CompanyScoreResponse output = CompanyScoreOutput.saveCompanyScore(companyScoreRequest);

        assertNotNull(output.getMsgId());
        assertEquals(companyScoreRequest.getMsgId(), output.getMsgId());
        assertEquals(companyScoreRequest.getCompanyName(), output.getCompanyName());
        assertEquals(companyScoreRequest.getDirectorsCount(), output.getDirectorsCount());
        assertEquals(companyScoreRequest.getRegistrationDate(), output.getRegistrationDate());
        assertEquals(companyScoreRequest.getLastUpdated(), output.getLastUpdated());
    }
}

package com.ak.companyscore.service;

import com.ak.companyscore.mongo.repository.CompanyScoreRepository;
import com.ak.companyscore.mongo.dao.CompanyScoreDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.math.BigInteger;
import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyScoreServiceTest {
    @Mock
    private CompanyScoreRepository companyScoreRepository;

    private CompanyScoreService companyScoreService;

    @BeforeEach
    void setUp() {
        companyScoreService = new CompanyScoreService(companyScoreRepository);
    }

    @Test
    public void saveCompanyScore_shouldSaveCompanyScoreInRepo() {
        var msgId = BigInteger.TEN;
        var companyName = "royco";
        var registrationDate = ZonedDateTime.now(UTC);
        var directorsCount = 45;
        var score = 56.55f;
        var lastUpdated = ZonedDateTime.now(UTC).plusHours(1);

        when(companyScoreRepository.save(any(CompanyScoreDao.class))).thenAnswer((Answer<CompanyScoreDao>) invocation -> invocation.getArgument(0));

        CompanyScoreDao companyScoreDao = companyScoreService.saveCompanyScore(msgId, companyName, registrationDate, directorsCount, score, lastUpdated);
        assertEquals(msgId, companyScoreDao.getMsgId());
        assertEquals(directorsCount, companyScoreDao.getDirectorsCount());
        assertEquals(companyName, companyScoreDao.getCompanyName());
        assertEquals(lastUpdated, companyScoreDao.getLastUpdated());
        assertEquals(score, companyScoreDao.getScore());
        assertEquals(registrationDate, companyScoreDao.getRegistrationDate());

        ArgumentCaptor<CompanyScoreDao> captor = ArgumentCaptor.forClass(CompanyScoreDao.class);
        verify(companyScoreRepository).save(captor.capture());
        CompanyScoreDao companyScoreDaoCaptor = captor.getValue();

        assertEquals(msgId, companyScoreDaoCaptor.getMsgId());
        assertEquals(directorsCount, companyScoreDaoCaptor.getDirectorsCount());
        assertEquals(companyName, companyScoreDaoCaptor.getCompanyName());
        assertEquals(lastUpdated, companyScoreDaoCaptor.getLastUpdated());
        assertEquals(score, companyScoreDaoCaptor.getScore());
        assertEquals(registrationDate, companyScoreDaoCaptor.getRegistrationDate());
    }
}

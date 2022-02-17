package com.ak.companyscore.service;

import com.ak.companyscore.mongo.dao.CompanyScoreDao;
import com.ak.companyscore.mongo.repository.CompanyScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class CompanyScoreService {
    public final CompanyScoreRepository repository;

    public CompanyScoreDao saveCompanyScore(
            BigInteger msgId,
            String companyName,
            ZonedDateTime registrationDate,
            Integer directorsCount,
            Float score,
            ZonedDateTime lastUpdated
    ) {
        var companyScoreDao = CompanyScoreDao.builder()
                .msgId(msgId)
                .companyName(companyName)
                .registrationDate(registrationDate)
                .directorsCount(directorsCount)
                .lastUpdated(lastUpdated)
                .score(score)
                .build();

        return repository.save(companyScoreDao);
    }
}

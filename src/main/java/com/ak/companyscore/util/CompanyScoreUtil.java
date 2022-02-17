package com.ak.companyscore.util;

import com.ak.companyscore.dto.CompanyScoreResponse;
import com.ak.companyscore.mongo.dao.CompanyScoreDao;

public final class CompanyScoreUtil {
    private CompanyScoreUtil() {
    }

    public static CompanyScoreResponse toCompanyScoreResponse(CompanyScoreDao companyScoreDao) {
        return CompanyScoreResponse.builder()
                .msgId(companyScoreDao.getMsgId())
                .companyName(companyScoreDao.getCompanyName())
                .registrationDate(companyScoreDao.getRegistrationDate())
                .directorsCount(companyScoreDao.getDirectorsCount())
                .lastUpdated(companyScoreDao.getLastUpdated())
                .score(companyScoreDao.getScore())
                .build();
    }
}

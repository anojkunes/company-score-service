package com.ak.companyscore.mongo.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@Data
@Builder(toBuilder = true)
@Document
@AllArgsConstructor
public class CompanyScoreDao {
    @Id
    BigInteger msgId;
    String companyName;
    ZonedDateTime registrationDate;
    Integer directorsCount;
    Float score;
    ZonedDateTime lastUpdated;
}

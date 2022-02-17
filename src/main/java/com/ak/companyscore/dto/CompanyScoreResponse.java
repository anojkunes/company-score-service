package com.ak.companyscore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@Value
@Builder
public class CompanyScoreResponse {
    @JsonProperty("msg_id")
    BigInteger msgId;
    @JsonProperty("company_name")
    String companyName;
    Float score;
    @JsonProperty("registration_date")
    ZonedDateTime registrationDate;
    @JsonProperty("directors_count")
    Integer directorsCount;
    @JsonProperty("last_updated")
    ZonedDateTime lastUpdated;
}

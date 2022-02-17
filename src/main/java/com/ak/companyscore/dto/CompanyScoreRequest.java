package com.ak.companyscore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Value
@Builder
public class CompanyScoreRequest {
    @NotNull
    @JsonProperty("msg_id")
    BigInteger msgId;
    @NotNull
    @JsonProperty("company_name")
    String companyName;
    @NotNull
    @JsonProperty("registration_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    ZonedDateTime registrationDate;
    @NotNull
    Float score;
    @NotNull
    @JsonProperty("directors_count")
    Integer directorsCount;
    @NotNull
    @JsonProperty("last_updated")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    ZonedDateTime lastUpdated;
}

package com.ak.companyscore.api;

import com.ak.companyscore.dto.CompanyScoreRequest;
import com.ak.companyscore.dto.CompanyScoreResponse;
import com.ak.companyscore.service.CompanyScoreService;
import com.ak.companyscore.util.CompanyScoreUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/scores", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CompanyScoreController {

    private final CompanyScoreService companyScoreService;

    @PutMapping
    public CompanyScoreResponse saveCompanyScore(@RequestBody @Valid CompanyScoreRequest request) {

        var companyScoreDao = companyScoreService.saveCompanyScore(
                request.getMsgId(),
                request.getCompanyName(),
                request.getRegistrationDate(),
                request.getDirectorsCount(),
                request.getScore(),
                request.getLastUpdated()
        );

        return CompanyScoreUtil.toCompanyScoreResponse(companyScoreDao);
    }
}

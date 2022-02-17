package com.ak.companyscore.integration.mocks

import com.ak.companyscore.dto.CompanyScoreResponse
import com.ak.companyscore.dto.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.ak.companyscore.mongo.repository.CompanyScoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = com.ak.companyscore.Application.class, properties = [
        "spring.mongodb.embedded.version:3.5.5"
])
@ContextConfiguration
@AutoConfigureMockMvc
abstract class BaseMockTest extends Specification {

    @Autowired
    protected MockMvc mvc

    @Autowired
    protected CompanyScoreRepository repository;

    @Autowired
    ObjectMapper mapper

    def "setup"() {
        repository.deleteAll();
    }

    def saveCompanyScore(String request, int status = 200) {
        def result = mvc.perform(put("/v1/scores")
                .contentType(APPLICATION_JSON_VALUE)
                .content(request))

        assert result.andReturn().response.status == status

        if (status != 200) {
            return mapper.readValue(result.andReturn().response.contentAsString, ErrorResponse.class)
        }

        return mapper.readValue(result.andReturn().response.contentAsString, CompanyScoreResponse.class)
    }

}

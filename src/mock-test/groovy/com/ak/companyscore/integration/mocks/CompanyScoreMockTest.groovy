package com.ak.companyscore.integration.mocks


import com.ak.companyscore.dto.ErrorResponse
import com.ak.companyscore.dto.CompanyScoreResponse

import java.time.ZonedDateTime

class CompanyScoreMockTest extends BaseMockTest {
    def "Create Company Score and then Update some fields"() {
        when: "Creating Company Score"
        def createRequest = """
            {
                "msg_id": 5,
                "score": 55.6,
                "company_name": "GoCity",
                "registration_date": "2020-10-27T14:34:15.333Z",
                "directors_count": 0,
                "last_updated": "2020-10-27T14:34:06.315Z"
            }
"""
        def createResponse = (CompanyScoreResponse) saveCompanyScore(createRequest)

        then: "Assert result for created company score"
        createResponse.msgId == BigInteger.valueOf(5)
        createResponse.companyName == "GoCity"
        createResponse.registrationDate == ZonedDateTime.parse("2020-10-27T14:34:15.333Z")
        createResponse.lastUpdated == ZonedDateTime.parse("2020-10-27T14:34:06.315Z")
        createResponse.directorsCount == 0
        createResponse.score == 55.6f
        repository.existsById(createResponse.msgId)

        when: "Query from Repository to see if data is persisted"
        def createCompanyScoreDao = repository.findById(BigInteger.valueOf(5))

        then: "Assert result for created company score from DB"
        createCompanyScoreDao.present
        createCompanyScoreDao.get().msgId == BigInteger.valueOf(5)
        createCompanyScoreDao.get().companyName == "GoCity"
        createCompanyScoreDao.get().registrationDate == ZonedDateTime.parse("2020-10-27T14:34:15.333Z")
        createCompanyScoreDao.get().lastUpdated == ZonedDateTime.parse("2020-10-27T14:34:06.315Z")
        createCompanyScoreDao.get().directorsCount == 0
        createCompanyScoreDao.get().score == 55.6f

        when: "Updating Company Score"
        def updateRequest = """
            {
                "msg_id": 5,
                "score": 65.6,
                "company_name": "royco",
                "registration_date": "2020-10-27T15:34:15.333Z",
                "directors_count": 2,
                "last_updated": "2020-10-28T14:34:06.315Z"
            }
"""
        def updateResponse = (CompanyScoreResponse) saveCompanyScore(updateRequest)

        then: "Assert result for updated company score"
        updateResponse.msgId == BigInteger.valueOf(5)
        updateResponse.companyName == "royco"
        updateResponse.registrationDate == ZonedDateTime.parse("2020-10-27T15:34:15.333Z")
        updateResponse.lastUpdated == ZonedDateTime.parse("2020-10-28T14:34:06.315Z")
        updateResponse.directorsCount == 2
        updateResponse.score == 65.6f
        repository.existsById(updateResponse.msgId)

        when: "Find the Updated Company Score From a repository query"
        def updatedCompanyScoreDao = repository.findById(BigInteger.valueOf(5))

        then: "Assert result for Updated company score from DB"
        updatedCompanyScoreDao.present
        updatedCompanyScoreDao.get().msgId == BigInteger.valueOf(5)
        updatedCompanyScoreDao.get().companyName == "royco"
        updatedCompanyScoreDao.get().registrationDate == ZonedDateTime.parse("2020-10-27T15:34:15.333Z")
        updatedCompanyScoreDao.get().lastUpdated == ZonedDateTime.parse("2020-10-28T14:34:06.315Z")
        updatedCompanyScoreDao.get().directorsCount == 2
        updatedCompanyScoreDao.get().score == 65.6f

        when: "Find record counts in Company Score Collection"
        def count = repository.count()

        then: "There should be 1 record"
        count == 1
    }

    def "Create 2 Company Scores"() {
        when: "Creating 1st Company Score "
        def createFirstRequest = """
            {
                "msg_id": 1,
                "company_name": "GoCity",
                "registration_date": "2020-10-27T14:34:15.333Z",
                "directors_count": 0,
                "score": 65.633,
                "last_updated": "2020-10-27T14:34:06.315Z"
            }
"""
        def createFirstResponse = (CompanyScoreResponse) saveCompanyScore(createFirstRequest)

        then: "Assert result for created company score"
        createFirstResponse.msgId == BigInteger.valueOf(1)
        createFirstResponse.companyName == "GoCity"
        createFirstResponse.registrationDate == ZonedDateTime.parse("2020-10-27T14:34:15.333Z")
        createFirstResponse.lastUpdated == ZonedDateTime.parse("2020-10-27T14:34:06.315Z")
        createFirstResponse.directorsCount == 0
        createFirstResponse.score == 65.633f
        repository.existsById(createFirstResponse.msgId)

        when: "Query from Repository to see if data is persisted"
        def createFirstCompanyScoreDao = repository.findById(BigInteger.valueOf(1))

        then: "Assert result for created company score from DB"
        createFirstCompanyScoreDao.present
        createFirstCompanyScoreDao.get().msgId == BigInteger.valueOf(1)
        createFirstCompanyScoreDao.get().companyName == "GoCity"
        createFirstCompanyScoreDao.get().registrationDate == ZonedDateTime.parse("2020-10-27T14:34:15.333Z")
        createFirstCompanyScoreDao.get().lastUpdated == ZonedDateTime.parse("2020-10-27T14:34:06.315Z")
        createFirstCompanyScoreDao.get().directorsCount == 0
        createFirstCompanyScoreDao.get().score == 65.633f

        when: "Creating 2nd Company Score"
        def createSecondRequest = """
            {
                "msg_id": 2,
                "score": 89,
                "company_name": "royco",
                "registration_date": "2020-10-27T15:34:15.333Z",
                "directors_count": 2,
                "last_updated": "2020-10-28T14:34:06.315Z"
            }
"""
        def createdSecondResponse = (CompanyScoreResponse) saveCompanyScore(createSecondRequest)

        then: "Assert result for created 2nd company score"
        createdSecondResponse.msgId == BigInteger.valueOf(2)
        createdSecondResponse.companyName == "royco"
        createdSecondResponse.registrationDate == ZonedDateTime.parse("2020-10-27T15:34:15.333Z")
        createdSecondResponse.lastUpdated == ZonedDateTime.parse("2020-10-28T14:34:06.315Z")
        createdSecondResponse.directorsCount == 2
        createdSecondResponse.score == 89f
        repository.existsById(createdSecondResponse.msgId)

        when: "Query from Repository to see if data is persisted"
        def createdSecondCompanyScoreDao = repository.findById(BigInteger.valueOf(2))

        then: "Assert result for 2nd company score from DB"
        createdSecondCompanyScoreDao.present
        createdSecondCompanyScoreDao.get().msgId == BigInteger.valueOf(2)
        createdSecondCompanyScoreDao.get().companyName == "royco"
        createdSecondCompanyScoreDao.get().registrationDate == ZonedDateTime.parse("2020-10-27T15:34:15.333Z")
        createdSecondCompanyScoreDao.get().lastUpdated == ZonedDateTime.parse("2020-10-28T14:34:06.315Z")
        createdSecondCompanyScoreDao.get().directorsCount == 2
        createdSecondCompanyScoreDao.get().score == 89f

        when: "Find record counts in Company Score Collection"
        def count = repository.count()

        then: "There should be 2 records"
        count == 2
    }

    def "Create Company Score - Fails due to validation"() {
        when: "Trying to Create Company Score "
        def createFirstRequest = """
            {
            }
"""
        def errorResponse = (ErrorResponse) saveCompanyScore(createFirstRequest, 400)

        then: "Assert result"
        errorResponse.errorCode == "TT-002"
        errorResponse.errorMessage.contains("companyName must not be null")
        errorResponse.errorMessage.contains("msgId must not be null")
        errorResponse.errorMessage.contains("lastUpdated must not be null")
        errorResponse.errorMessage.contains("directorsCount must not be null")
        errorResponse.errorMessage.contains("registrationDate must not be null")
        errorResponse.errorMessage.contains("score must not be null")
    }

    def "Create Company Score - Fails due to Malformed Request on Date as expected date format to be yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"() {
        when: "Trying to Create Company Score "
        def createRequest = """
            {
                "msg_id": 5,
                "score": 5.55,
                "company_name": "GoCity",
                "registration_date": "$registration_date",
                "directors_count": 0,
                "last_updated": "$last_updated"
            }
"""
        def errorResponse = (ErrorResponse) saveCompanyScore(createRequest, 400)

        then: "Assert result"
        errorResponse.errorCode == "TT-001"
        errorResponse.errorMessage == "Malformed request"

        where:
        registration_date | last_updated
        "2020-10-27T14:34:15.333+0530" | "2020-10-27T14:34:06.315+0530"
        "2020-10-27T14:34:15.333+0000" | "2020-10-27T14:34:06.315+0000"
        "2020-10-27T14:34:15.333" | "2020-10-27T14:34:06.315"
        "2020-10-27T14:34:15.333Z" | "2020-10-27T14:34:06.315"
    }
}

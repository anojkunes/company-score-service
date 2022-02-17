package com.ak.companyscore.mongo.repository;

import com.ak.companyscore.mongo.dao.CompanyScoreDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CompanyScoreRepository extends MongoRepository<CompanyScoreDao, BigInteger> {
}

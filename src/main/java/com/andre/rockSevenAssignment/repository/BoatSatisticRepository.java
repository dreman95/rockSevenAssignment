package com.andre.rockSevenAssignment.repository;

import com.andre.rockSevenAssignment.model.BoatSatistic;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BoatSatisticRepository extends MongoRepository<BoatSatistic, String> {
    @Query("{'teamName': '?0'}")
    public BoatSatistic findByName(String teamName);
}

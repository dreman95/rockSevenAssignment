package com.andre.rockSevenAssignment.repository;

import java.util.List;

import com.andre.rockSevenAssignment.model.BoatTeam;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BoatTeamRepository extends MongoRepository<BoatTeam, String> {

  @Query("{'positions.gpsAt' : '?0'}")
  public List<BoatTeam> findBytimeStamp(String pos);

}
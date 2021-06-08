package com.andre.rockSevenAssignment.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
/*
BoatTeam - Used to map to the boatTeam collection in the Mongo DB
*/
public class BoatTeam {

  @Id
  private String id;

  private Long marker;
  private String name;
  private Long serial;
  private ArrayList<Positions> positions;

  public BoatTeam() {}
    
  public BoatTeam(Long marker, String name, Long serial, ArrayList<Positions> positions) {
    this.marker = marker;
    this.name = name;
    this.serial = serial;
    this.positions = positions;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getMarker() {
    return marker;
  }

  public void setMarker(Long marker) {
    this.marker = marker;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getSerial() {
    return serial;
  }

  public void setSerial(Long serial) {
    this.serial = serial;
  }

  public ArrayList<Positions> getPositions() {
    return positions;
  }

  public void setPositions(ArrayList<Positions> positions) {
    this.positions = positions;
  }
}
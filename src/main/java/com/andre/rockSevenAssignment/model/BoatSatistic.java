package com.andre.rockSevenAssignment.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
/*
BoatSatistic - Used to map to the boatSatistic collection in the Mongo DB
*/
public class BoatSatistic {
    @Id
    private String id;
    private String teamName;
    private ArrayList<Sightings> sightings = new ArrayList();
    
	public BoatSatistic(String teamName, Sightings sighting) {
		super();
		this.teamName = teamName;
		this.sightings.add(sighting);
	}
	
	public BoatSatistic(String teamName, ArrayList<Sightings> sightings) {
		super();
		this.teamName = teamName;
		this.sightings = sightings;
	}
	
	public BoatSatistic() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public ArrayList<Sightings> getSightings() {
		return sightings;
	}

	public void setSightings(Sightings sighting) {
		this.sightings.add(sighting);

	}
}

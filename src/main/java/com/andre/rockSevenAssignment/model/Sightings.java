package com.andre.rockSevenAssignment.model;

/*
Sightings - Maps to the sightings array in the boatSatistic collection in Mongo DB
*/
public class Sightings {
	
	public Sightings(int dayOfRace, String dateSeen, int numOfBoatsSeen) {
		super();
		this.dayOfRace = dayOfRace;
		this.dateSeen = dateSeen;
		this.numOfBoatsSeen = numOfBoatsSeen;
	}
	
	private int dayOfRace;
	private String dateSeen;
	private int numOfBoatsSeen;
	
	public int getDayOfRace() {
		return dayOfRace;
	}
	public void setDayOfRace(int dayOfRace) {
		this.dayOfRace = dayOfRace;
	}
	public String getDateSeen() {
		return dateSeen;
	}
	public void setDateSeen(String dateSeen) {
		this.dateSeen = dateSeen;
	}
	public int getNumOfBoatsSeen() {
		return numOfBoatsSeen;
	}
	public void setNumOfBoatsSeen(int numOfBoatsSeen) {
		this.numOfBoatsSeen = numOfBoatsSeen;
	}
}

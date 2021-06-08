package com.andre.rockSevenAssignment.model;

/*
AverageSightings - A POJO class used for the final output of the table.
*/
public class AverageSightings {
    private String name;
    private int day;
    private int averageNoSightings;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public int getAverageNoSightings() {
        return averageNoSightings;
    }
    public void setAverageNoSightings(int averageNoSightings) {
        this.averageNoSightings = averageNoSightings;
    }  
}

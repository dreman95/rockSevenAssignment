package com.andre.rockSevenAssignment.model;

/*
Positions - Used to map to each object in the positions array 
            in the boaTeam collection in the Mongo DB
*/
public class Positions {
    private boolean alert;
    private double altitude;
    private String type;
    private Double dtfkm;
    private double id;
    private String gpsAt;
    private Double sogKnots;
    private Long battery;
    private Long cog;
    private Double dtfNm;
    private String txAt;
    private Double longitude;
    private Double latitude;
    private Long gpsAtMillis;
    private Double sogKmph;

    public boolean isAlert() {
        return alert;
    }
    public void setAlert(boolean alert) {
        this.alert = alert;
    }
    public double getAltitude() {
        return altitude;
    }
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Double getDtfkm() {
        return dtfkm;
    }
    public void setDtfkm(Double dtfkm) {
        this.dtfkm = dtfkm;
    }
    public double getId() {
        return id;
    }
    public void setId(double id) {
        this.id = id;
    }
    public String getGpsAt() {
        return gpsAt;
    }
    public void setGpsAt(String gpsAt) {
        this.gpsAt = gpsAt;
    }
    public Double getSogKnots() {
        return sogKnots;
    }
    public void setSogKnots(Double sogKnots) {
        this.sogKnots = sogKnots;
    }
    public Long getBattery() {
        return battery;
    }
    public void setBattery(Long battery) {
        this.battery = battery;
    }
    public Long getCog() {
        return cog;
    }
    public void setCog(Long cog) {
        this.cog = cog;
    }
    public Double getDtfNm() {
        return dtfNm;
    }
    public void setDtfNm(Double long1) {
        this.dtfNm = long1;
    }
    public String getTxAt() {
        return txAt;
    }
    public void setTxAt(String txAt) {
        this.txAt = txAt;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Long getGpsAtMillis() {
        return gpsAtMillis;
    }
    public void setGpsAtMillis(Long gpsAtMillis) {
        this.gpsAtMillis = gpsAtMillis;
    }
    public Double getSogKmph() {
        return sogKmph;
    }
    public void setSogKmph(Double sogKmph) {
        this.sogKmph = sogKmph;
    }    
}

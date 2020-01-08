package com.github.franckyi.projettransversal.common.model;

public class Caserne {

    private int idCaserne;
    private double longitude;
    private double latitude;

    public Caserne() {
    }

    public Caserne(double longitude, double latitude) {
        this(0, longitude, latitude);
    }

    public Caserne(int idCaserne, double longitude, double latitude) {
        this.idCaserne = idCaserne;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getIdCaserne() {
        return idCaserne;
    }

    public void setIdCaserne(int idCaserne) {
        this.idCaserne = idCaserne;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

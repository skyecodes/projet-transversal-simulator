package com.github.franckyi.projettransversal.common.model;

public class Camion {

    private int idCamion;
    private int idCaserne;
    private double longitude;
    private double latitude;

    public Camion() {
    }

    public Camion(int idCaserne, double longitude, double latitude) {
        this(0, idCaserne, longitude, latitude);
    }

    public Camion(int idCamion, int idCaserne, double longitude, double latitude) {
        this.idCamion = idCamion;
        this.idCaserne = idCaserne;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
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

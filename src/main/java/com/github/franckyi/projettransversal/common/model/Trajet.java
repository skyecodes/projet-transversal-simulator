package com.github.franckyi.projettransversal.common.model;

import com.github.franckyi.projettransversal.common.dao.TrajetDAO;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "trajets", daoClass = TrajetDAO.class)
public class Trajet implements Position {

    @DatabaseField(generatedId = true, columnName = "id_trajet")
    private int idTrajet;

    @DatabaseField(canBeNull = false, foreign = true, columnName = "id_camion")
    private Camion camion;

    @DatabaseField(columnName = "ordre")
    private int ordre;

    @DatabaseField(columnName = "longitude")
    private double longitude;

    @DatabaseField(columnName = "latitude")
    private double latitude;

    public Trajet() {
    }

    public Trajet(Camion camion, int ordre, double longitude, double latitude) {
        this(0, camion, ordre, longitude, latitude);
    }

    public Trajet(int idTrajet, Camion camion, int ordre, double longitude, double latitude) {
        this.idTrajet = idTrajet;
        this.camion = camion;
        this.ordre = ordre;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(int idTrajet) {
        this.idTrajet = idTrajet;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
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

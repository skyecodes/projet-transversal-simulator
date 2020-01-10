package com.github.franckyi.projettransversal.common.model;

import com.github.franckyi.projettransversal.common.dao.CamionDAO;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "camions", daoClass = CamionDAO.class)
public class Camion implements Position {

    @DatabaseField(generatedId = true, columnName = "id_camion")
    private int idCamion;

    @DatabaseField(canBeNull = false, foreign = true, columnName = "id_caserne")
    private Caserne caserne;

    @DatabaseField(columnName = "longitude")
    private double longitude;

    @DatabaseField(columnName = "latitude")
    private double latitude;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<Intervention> interventions;

    public Camion() {
    }

    public Camion(Caserne caserne) {
        this(caserne, caserne.getLongitude(), caserne.getLatitude());
    }

    public Camion(Caserne caserne, double longitude, double latitude) {
        this(0, caserne, longitude, latitude);
    }

    public Camion(int idCamion, Caserne caserne, double longitude, double latitude) {
        this.idCamion = idCamion;
        this.caserne = caserne;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
    }

    public Caserne getCaserne() {
        return caserne;
    }

    public void setCaserne(Caserne caserne) {
        this.caserne = caserne;
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

    public ForeignCollection<Intervention> getInterventions() {
        return interventions;
    }

    public void setInterventions(ForeignCollection<Intervention> interventions) {
        this.interventions = interventions;
    }
}

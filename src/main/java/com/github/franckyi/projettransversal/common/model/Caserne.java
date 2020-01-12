package com.github.franckyi.projettransversal.common.model;

import com.github.franckyi.projettransversal.common.dao.CaserneDAO;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "casernes", daoClass = CaserneDAO.class)
public class Caserne implements Pos {

    @DatabaseField(generatedId = true, columnName = "id_caserne")
    private int idCaserne;

    @DatabaseField(columnName = "longitude")
    private double longitude;

    @DatabaseField(columnName = "latitude")
    private double latitude;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<Camion> camions;

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

    public ForeignCollection<Camion> getCamions() {
        return camions;
    }

    public void setCamions(ForeignCollection<Camion> camions) {
        this.camions = camions;
    }
}

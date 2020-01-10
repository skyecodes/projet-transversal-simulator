package com.github.franckyi.projettransversal.common.model;

import com.github.franckyi.projettransversal.common.dao.FeuDAO;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "feux", daoClass = FeuDAO.class)
public class Feu {

    @DatabaseField(generatedId = true, columnName = "id_feu")
    private int idFeu;

    @DatabaseField(canBeNull = false, foreign = true, columnName = "id_point")
    private Point point;

    @DatabaseField(columnName = "intensite")
    private int intensite;

    @DatabaseField(columnName = "date")
    private Date date;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<Intervention> interventions;

    public Feu() {
    }

    public Feu(Point point, int intensite, Date date) {
        this(0, point, intensite, date);
    }

    public Feu(int idFeu, Point point, int intensite, Date date) {
        this.idFeu = idFeu;
        this.point = point;
        this.intensite = intensite;
        this.date = date;
    }

    public int getIdFeu() {
        return idFeu;
    }

    public void setIdFeu(int idFeu) {
        this.idFeu = idFeu;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getIntensite() {
        return intensite;
    }

    public void setIntensite(int intensite) {
        this.intensite = intensite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ForeignCollection<Intervention> getInterventions() {
        return interventions;
    }

    public void setInterventions(ForeignCollection<Intervention> interventions) {
        this.interventions = interventions;
    }
}

package com.github.franckyi.projettransversal.common.model;

import java.sql.Timestamp;

public class Feu {

    private int idFeu;
    private int idPoint;
    private int intensite;
    private Timestamp date;

    public Feu() {
    }

    public Feu(int idPoint, int intensite, Timestamp date) {
        this(0, idPoint, intensite, date);
    }

    public Feu(int idFeu, int idPoint, int intensite, Timestamp date) {
        this.idFeu = idFeu;
        this.idPoint = idPoint;
        this.intensite = intensite;
        this.date = date;
    }

    public int getIdFeu() {
        return idFeu;
    }

    public void setIdFeu(int idFeu) {
        this.idFeu = idFeu;
    }

    public int getIdPoint() {
        return idPoint;
    }

    public void setIdPoint(int idPoint) {
        this.idPoint = idPoint;
    }

    public int getIntensite() {
        return intensite;
    }

    public void setIntensite(int intensite) {
        this.intensite = intensite;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}

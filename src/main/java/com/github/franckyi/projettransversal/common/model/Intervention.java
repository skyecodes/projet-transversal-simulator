package com.github.franckyi.projettransversal.common.model;

import com.github.franckyi.projettransversal.common.dao.InterventionDAO;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "interventions", daoClass = InterventionDAO.class)
public class Intervention {

    @DatabaseField(generatedId = true, columnName = "id_intervention")
    private int idIntervention;

    @DatabaseField(canBeNull = false, foreign = true, columnName = "id_feu")
    private Feu feu;

    @DatabaseField(canBeNull = false, foreign = true, columnName = "id_camion")
    private Camion camion;

    @DatabaseField(columnName = "trajet_actuel")
    private int trajetActuel;

    public Intervention() {
    }

    public Intervention(Feu feu, Camion camion) {
        this(0, feu, camion, 0);
    }

    public Intervention(int idIntervention, Feu feu, Camion camion, int trajetActuel) {
        this.idIntervention = idIntervention;
        this.feu = feu;
        this.camion = camion;
        this.trajetActuel = trajetActuel;
    }

    public int getIdIntervention() {
        return idIntervention;
    }

    public void setIdIntervention(int idIntervention) {
        this.idIntervention = idIntervention;
    }

    public Feu getFeu() {
        return feu;
    }

    public void setFeu(Feu feu) {
        this.feu = feu;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public int getTrajetActuel() {
        return trajetActuel;
    }

    public void setTrajetActuel(int trajetActuel) {
        this.trajetActuel = trajetActuel;
    }
}

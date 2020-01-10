package com.github.franckyi.projettransversal.common.dao;

import com.github.franckyi.projettransversal.common.model.Camion;
import com.github.franckyi.projettransversal.common.model.Intervention;
import com.github.franckyi.projettransversal.common.model.Trajet;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrajetDAO extends BaseDaoImpl<Trajet, Integer> {

    public TrajetDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Trajet.class);
    }

    public Trajet queryNext(Intervention intervention) throws SQLException {
        return this.queryWithOrdre(intervention, intervention.getTrajetActuel() + 1);
    }

    public Trajet queryPrevious(Intervention intervention) throws SQLException {
        return this.queryWithOrdre(intervention, intervention.getTrajetActuel() - 1);
    }

    private Trajet queryWithOrdre(Intervention intervention, int ordre) throws SQLException {
        Map<String, Object> values = new HashMap<>();
        values.put("id_camion", intervention.getCamion().getIdCamion());
        values.put("ordre", ordre);
        List<Trajet> trajets = this.queryForFieldValues(values);
        return (trajets.size() == 1) ? trajets.get(0) : null;
    }

    public void deleteForCamion(Camion camion) throws SQLException {
        DeleteBuilder<Trajet, Integer> builder = this.deleteBuilder();
        builder.where().eq("id_camion", camion.getIdCamion());
        builder.delete();
    }
}

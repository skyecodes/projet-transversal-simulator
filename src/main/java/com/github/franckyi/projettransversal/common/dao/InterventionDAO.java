package com.github.franckyi.projettransversal.common.dao;

import com.github.franckyi.projettransversal.common.model.Intervention;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class InterventionDAO extends BaseDaoImpl<Intervention, Integer> {

    public InterventionDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Intervention.class);
    }

}

package com.github.franckyi.projettransversal.common.dao;

import com.github.franckyi.projettransversal.common.model.Camion;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class CamionDAO extends BaseDaoImpl<Camion, Integer> {

    public CamionDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Camion.class);
    }

}

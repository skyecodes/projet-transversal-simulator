package com.github.franckyi.projettransversal.common.dao;

import com.github.franckyi.projettransversal.common.model.Caserne;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class CaserneDAO extends BaseDaoImpl<Caserne, Integer> {

    public CaserneDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Caserne.class);
    }

}

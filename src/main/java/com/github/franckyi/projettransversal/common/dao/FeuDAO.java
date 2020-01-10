package com.github.franckyi.projettransversal.common.dao;

import com.github.franckyi.projettransversal.common.model.Feu;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class FeuDAO extends BaseDaoImpl<Feu, Integer> {

    public FeuDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Feu.class);
    }

}

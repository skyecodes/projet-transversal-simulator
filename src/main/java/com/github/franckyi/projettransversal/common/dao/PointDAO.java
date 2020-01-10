package com.github.franckyi.projettransversal.common.dao;

import com.github.franckyi.projettransversal.common.model.Point;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class PointDAO extends BaseDaoImpl<Point, Integer> {

    public PointDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Point.class);
    }

}

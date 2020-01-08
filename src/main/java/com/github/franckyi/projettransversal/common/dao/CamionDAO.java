package com.github.franckyi.projettransversal.common.dao;

import com.github.franckyi.projettransversal.common.model.Camion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CamionDAO extends DAO<Camion> {

    public CamionDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected boolean doCreate(Camion obj) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO camions (id_caserne, longitude, latitude) VALUES (?, ?, ?)");
        stmt.setInt(1, obj.getIdCaserne());
        stmt.setDouble(2, obj.getLongitude());
        stmt.setDouble(3, obj.getLatitude());
        return stmt.execute();
    }

    @Override
    protected boolean doDelete(Camion obj) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM camions WHERE id_camion = ?");
        stmt.setInt(1, obj.getIdCamion());
        return stmt.execute();
    }

    @Override
    protected boolean doUpdate(Camion obj) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE camions SET id_caserne = ?, longitude = ?, latitude = ? WHERE id_camion = ?");
        stmt.setInt(1, obj.getIdCaserne());
        stmt.setDouble(2, obj.getLongitude());
        stmt.setDouble(3, obj.getLatitude());
        stmt.setInt(4, obj.getIdCamion());
        return stmt.execute();
    }

    @Override
    protected Camion doFind(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM camions WHERE id_camion = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Camion(id,
                    rs.getInt("id_caserne"),
                    rs.getDouble("longitude"),
                    rs.getDouble("latitude")
            );
        }
        return null;
    }
}

package com.github.franckyi.projettransversal.common.dao;

import com.github.franckyi.projettransversal.common.model.Caserne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CaserneDAO extends DAO<Caserne> {

    public CaserneDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected boolean doCreate(Caserne obj) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO casernes (longitude, latitude) VALUES (?, ?)");
        stmt.setDouble(1, obj.getLongitude());
        stmt.setDouble(2, obj.getLatitude());
        return stmt.execute();
    }

    @Override
    protected boolean doDelete(Caserne obj) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM casernes WHERE id_caserne = ?");
        stmt.setInt(1, obj.getIdCaserne());
        return stmt.execute();
    }

    @Override
    protected boolean doUpdate(Caserne obj) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE casernes SET longitude = ?, latitude = ? WHERE id_caserne = ?");
        stmt.setDouble(1, obj.getLongitude());
        stmt.setDouble(2, obj.getLatitude());
        stmt.setInt(3, obj.getIdCaserne());
        return stmt.execute();
    }

    @Override
    protected Caserne doFind(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM casernes WHERE id_caserne = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Caserne(id,
                    rs.getDouble("longitude"),
                    rs.getDouble("latitude")
            );
        }
        return null;
    }
}

package com.github.franckyi.projettransversal.common.dao;

import com.github.franckyi.projettransversal.common.model.Point;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PointDAO extends DAO<Point> {

    public PointDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected boolean doCreate(Point obj) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO points (colonne, ligne, longitude, latitude) VALUES (?, ?, ?, ?)");
        stmt.setInt(1, obj.getColonne());
        stmt.setInt(2, obj.getLigne());
        stmt.setDouble(3, obj.getLongitude());
        stmt.setDouble(4, obj.getLatitude());
        return stmt.execute();
    }

    @Override
    protected boolean doDelete(Point obj) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM points WHERE id_point = ?");
        stmt.setInt(1, obj.getIdPoint());
        return stmt.execute();
    }

    @Override
    protected boolean doUpdate(Point obj) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE points SET colonne = ?, ligne = ?, longitude = ?, latitude = ? WHERE id_point = ?");
        stmt.setInt(1, obj.getColonne());
        stmt.setInt(2, obj.getLigne());
        stmt.setDouble(3, obj.getLongitude());
        stmt.setDouble(4, obj.getLatitude());
        stmt.setInt(5, obj.getIdPoint());
        return stmt.execute();
    }

    @Override
    protected Point doFind(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM points WHERE id_point = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Point(id,
                    rs.getInt("colonne"),
                    rs.getInt("ligne"),
                    rs.getDouble("longitude"),
                    rs.getDouble("latitude")
            );
        }
        return null;
    }
}

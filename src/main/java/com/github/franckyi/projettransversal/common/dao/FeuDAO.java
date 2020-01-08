package com.github.franckyi.projettransversal.common.dao;

import com.github.franckyi.projettransversal.common.model.Feu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeuDAO extends DAO<Feu> {

    public FeuDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected boolean doCreate(Feu obj) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO feux (id_point, intensite, date) VALUES (?, ?, ?)");
        stmt.setInt(1, obj.getIdPoint());
        stmt.setInt(2, obj.getIntensite());
        stmt.setTimestamp(3, obj.getDate());
        return stmt.execute();
    }

    @Override
    protected boolean doDelete(Feu obj) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM feux WHERE id_feu = ?");
        stmt.setInt(1, obj.getIdFeu());
        return stmt.execute();
    }

    @Override
    protected boolean doUpdate(Feu obj) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE feux SET id_point = ?, intensite = ?, date = ? WHERE id_feu = ?");
        stmt.setInt(1, obj.getIdPoint());
        stmt.setInt(2, obj.getIntensite());
        stmt.setTimestamp(3, obj.getDate());
        stmt.setInt(4, obj.getIdFeu());
        return stmt.execute();
    }

    @Override
    protected Feu doFind(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM feux WHERE id_feu = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Feu(id,
                    rs.getInt("id_point"),
                    rs.getInt("intensite"),
                    rs.getTimestamp("date")
            );
        }
        return null;
    }
}

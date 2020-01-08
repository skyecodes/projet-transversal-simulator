package com.github.franckyi.projettransversal.common;

import com.github.franckyi.projettransversal.common.dao.DAOFactory;
import com.github.franckyi.projettransversal.common.model.Point;

import java.sql.Connection;
import java.sql.SQLException;

public class PointGenerator {

    public static void main(String[] args) {
        try {
            Connection conn = ConnectionHandler.getConnection();
            conn.setAutoCommit(false);
            conn.createStatement().execute("TRUNCATE TABLE points RESTART IDENTITY");
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 6; j++) {
                    DAOFactory.getPointDAO().create(new Point(i, j, 0, 0));
                }
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

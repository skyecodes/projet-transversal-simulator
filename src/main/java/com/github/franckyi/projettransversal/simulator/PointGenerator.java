package com.github.franckyi.projettransversal.simulator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PointGenerator {

    public static void main(String[] args) {
        try {
            Connection conn = ConnectionHandler.getConnection();
            conn.setAutoCommit(false);
            conn.createStatement().execute("TRUNCATE TABLE Points RESTART IDENTITY");
            String query = "INSERT INTO Points VALUES (DEFAULT, ?, ?, ?, ?)";
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 6; j++) {
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setInt(1, i);
                    statement.setInt(2, j);
                    statement.setDouble(3, 0);
                    statement.setDouble(4, 0);
                    statement.execute();
                }
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

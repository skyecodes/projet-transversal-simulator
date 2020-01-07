package com.github.franckyi.projettransversal.simulator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

public class Simulator {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            createNewFire();
            System.out.println("---");
            Thread.sleep(RANDOM.nextInt(60000) + 30000);
        }
    }

    public static void createNewFire() {
        try {
            System.out.println("New fire");
            int row = RANDOM.nextInt(6);
            int col = RANDOM.nextInt(10);
            System.out.println(String.format("Row = %d; Col = %d", row, col));

            int pointId = getPointId(row, col);
            int intensity = RANDOM.nextInt(9) + 1;
            Timestamp date = Timestamp.from(Instant.now());
            System.out.println(String.format("PointID = %d; Intensity = %d; Date = %d", pointId, intensity, date.getTime()));

            insertFire(pointId, intensity, date);
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertFire(int pointId, int intensity, Timestamp date) throws SQLException {
        PreparedStatement stmt = ConnectionHandler.getConnection().prepareStatement("INSERT INTO feux(id_point, intensite, date) VALUES (?, ?, ?)");
        stmt.setInt(1, pointId);
        stmt.setInt(2, intensity);
        stmt.setTimestamp(3, date);
        stmt.execute();
        stmt.close();
    }

    private static int getPointId(int row, int col) throws SQLException {
        PreparedStatement stmt = ConnectionHandler.getConnection().prepareStatement("SELECT id_point FROM points WHERE ligne = ? AND colonne = ?");
        stmt.setInt(1, row);
        stmt.setInt(2, col);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            throw new SQLException(String.format("No point found for row=%d;col=%d", row, col));
        }
        int idPoint = rs.getInt("id_point");
        rs.close();
        stmt.close();
        return idPoint;
    }
}

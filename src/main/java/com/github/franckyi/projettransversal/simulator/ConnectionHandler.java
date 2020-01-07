package com.github.franckyi.projettransversal.simulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://postgresql-franckyi.alwaysdata.net:5432/franckyi_prj_simul";
                String user = "franckyi_projet";
                String passwd = "Projet2019!";
                connection = DriverManager.getConnection(url, user, passwd);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

}

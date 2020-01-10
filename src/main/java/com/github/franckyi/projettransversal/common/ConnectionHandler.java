package com.github.franckyi.projettransversal.common;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class ConnectionHandler {

    private static ConnectionSource connection;

    public static ConnectionSource getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                //Class.forName("org.postgresql.Driver");
                //String url = "jdbc:sqlserver://fire-sim.database.windows.net:1433;database=fire-sim;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
                String url = "jdbc:sqlserver://fire-sim.database.windows.net:1433;database=fire;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
                String user = "fire-sim-admin@fire-sim";
                String passwd = "prayforaustralia69*";
                connection = new JdbcConnectionSource(url, user, passwd);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

}

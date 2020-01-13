package com.github.franckyi.projettransversal.common;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class ConnectionHandler {

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL_SIM = "jdbc:sqlserver://fire-sim.database.windows.net:1433;database=fire-sim;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static final String URL_REEL = "jdbc:sqlserver://fire-sim.database.windows.net:1433;database=fire;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static final String USER = "fire-sim-admin@fire-sim";
    private static final String PASSWORD = "prayforaustralia69*";

    private static ConnectionSource connection;

    public static void init(Database db) throws ClassNotFoundException, SQLException {
        Class.forName(db.driver);
        connection = new JdbcConnectionSource(db.url, db.user, db.password);
    }

    public static ConnectionSource getConnection() {
        return connection;
    }

    public enum Database {
        SIMULATION(DRIVER, URL_SIM, USER, PASSWORD),
        REEL(DRIVER, URL_REEL, USER, PASSWORD);

        private String driver, url, user, password;

        Database(String driver, String url, String user, String password) {
            this.driver = driver;
            this.url = url;
            this.user = user;
            this.password = password;
        }
    }

}

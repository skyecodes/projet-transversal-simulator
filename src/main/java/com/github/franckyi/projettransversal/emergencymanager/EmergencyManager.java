package com.github.franckyi.projettransversal.emergencymanager;

import com.github.franckyi.projettransversal.common.ConnectionHandler;

import java.sql.SQLException;
import java.util.Timer;

public class EmergencyManager {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionHandler.init(ConnectionHandler.Database.REEL);
        Timer timer = new Timer();
        timer.schedule(new EmergencyManagerTask(), 0, 1000);
    }

}

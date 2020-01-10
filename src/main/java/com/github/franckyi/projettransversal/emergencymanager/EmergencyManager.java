package com.github.franckyi.projettransversal.emergencymanager;

import java.util.Timer;

public class EmergencyManager {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new EmergencyManagerTask(), 0, 3000);
    }

}

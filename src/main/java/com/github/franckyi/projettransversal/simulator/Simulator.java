package com.github.franckyi.projettransversal.simulator;

import com.github.franckyi.projettransversal.common.ConnectionHandler;
import com.github.franckyi.projettransversal.common.dao.DAOFactory;
import com.github.franckyi.projettransversal.common.model.Feu;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

public class Simulator {

    private static final Random RANDOM = new Random();

    // intervalle de temps entre 2 feux (secondes)
    private static final int MIN_INTERVAL = 30;
    private static final int MAX_INTERVAL = 90;

    public static void main(String[] args) throws InterruptedException, SQLException {
        if (args.length > 0) {
            if (args[0].equals("reset")) {
                System.out.println("Reset...");
                ConnectionHandler.getConnection().createStatement().execute("TRUNCATE TABLE feux RESTART IDENTITY");
                System.out.println("OK\n-----");
            }
        }
        while (true) {
            System.out.println("New fire");
            Feu feu = new Feu(
                    RANDOM.nextInt(60) + 1,
                    RANDOM.nextInt(9) + 1,
                    Timestamp.from(Instant.now())
            );
            System.out.println(String.format("idPoint=%d; intensite=%d; date=%d", feu.getIdPoint(), feu.getIntensite(), feu.getDate().getTime()));
            DAOFactory.getFeuDAO().create(feu);
            System.out.println("Done");

            int sleep = RANDOM.nextInt((MAX_INTERVAL - MIN_INTERVAL) * 1000) + MIN_INTERVAL * 1000;
            System.out.println(String.format("----- Waiting for %d seconds", sleep / 1000));
            Thread.sleep(sleep);
        }
    }
}

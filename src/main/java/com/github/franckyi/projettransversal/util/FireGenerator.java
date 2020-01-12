package com.github.franckyi.projettransversal.util;

import com.github.franckyi.projettransversal.common.dao.DAOFactory;
import com.github.franckyi.projettransversal.simulator.Simulator;

import java.sql.SQLException;

public class FireGenerator {

    private static final int QUANTITY = 1;

    public static void main(String[] args) throws SQLException {
        DAOFactory.getFeuDAO().callBatchTasks(() -> {
            for (int i = 0; i < QUANTITY; i++) {
                Simulator.generateFire();
            }
            return null;
        });
    }

}

package com.github.franckyi.projettransversal.util;

import com.github.franckyi.projettransversal.common.ConnectionHandler;
import com.github.franckyi.projettransversal.common.dao.DAOFactory;
import com.github.franckyi.projettransversal.common.model.Camion;
import com.github.franckyi.projettransversal.common.model.Caserne;

import java.sql.SQLException;
import java.util.Arrays;

public class DatabaseCleaner {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionHandler.init(ConnectionHandler.Database.REEL);
        DAOFactory.getCaserneDAO().executeRawNoArgs("TRUNCATE TABLE casernes");
        DAOFactory.getCamionDAO().executeRawNoArgs("TRUNCATE TABLE camions");
        DAOFactory.getFeuDAO().executeRawNoArgs("TRUNCATE TABLE feux");
        DAOFactory.getInterventionDAO().executeRawNoArgs("TRUNCATE TABLE interventions");
        DAOFactory.getTrajetDAO().executeRawNoArgs("TRUNCATE TABLE trajets");
        Caserne caserne1 = new Caserne(45.779049, 4.878112);
        Caserne caserne2 = new Caserne(45.750194, 4.848097);
        DAOFactory.getCaserneDAO().create(Arrays.asList(caserne1, caserne2));
        Camion camion11 = new Camion(caserne1);
        Camion camion12 = new Camion(caserne1);
        //Camion camion13 = new Camion(caserne1);
        Camion camion21 = new Camion(caserne2);
        Camion camion22 = new Camion(caserne2);
        //Camion camion23 = new Camion(caserne2);
        DAOFactory.getCamionDAO().create(Arrays.asList(camion11, camion12, camion21, camion22));
    }

}

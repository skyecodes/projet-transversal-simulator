package com.github.franckyi.projettransversal.util;

import com.github.franckyi.projettransversal.common.dao.DAOFactory;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseGenerator {

    public static void main(String[] args) throws SQLException {
        System.out.println("Dropping tables");
        //TableUtils.dropTable(DAOFactory.getCamionDAO(), true);
        //TableUtils.dropTable(DAOFactory.getCaserneDAO(), true);
        //TableUtils.dropTable(DAOFactory.getFeuDAO(), true);
        TableUtils.dropTable(DAOFactory.getInterventionDAO(), true);
        //TableUtils.dropTable(DAOFactory.getPointDAO(), true);
        //TableUtils.dropTable(DAOFactory.getTrajetDAO(), true);
        System.out.println("Creating tables");
        //TableUtils.createTable(DAOFactory.getCamionDAO());
        //TableUtils.createTable(DAOFactory.getCaserneDAO());
        //TableUtils.createTable(DAOFactory.getFeuDAO());
        TableUtils.createTable(DAOFactory.getInterventionDAO());
        //TableUtils.createTable(DAOFactory.getPointDAO());
        TableUtils.createTable(DAOFactory.getTrajetDAO());
        System.out.println("Done");
    }

}

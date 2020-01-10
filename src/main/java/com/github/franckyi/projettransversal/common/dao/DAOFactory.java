package com.github.franckyi.projettransversal.common.dao;

import com.github.franckyi.projettransversal.common.ConnectionHandler;
import com.github.franckyi.projettransversal.common.model.*;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class DAOFactory {

    private static final ConnectionSource source = ConnectionHandler.getConnection();

    public static CamionDAO getCamionDAO() {
        try {
            return DaoManager.createDao(source, Camion.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static CaserneDAO getCaserneDAO() {
        try {
            return DaoManager.createDao(source, Caserne.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static FeuDAO getFeuDAO() {
        try {
            return DaoManager.createDao(source, Feu.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static InterventionDAO getInterventionDAO() {
        try {
            return DaoManager.createDao(source, Intervention.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static PointDAO getPointDAO() {
        try {
            return DaoManager.createDao(source, Point.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TrajetDAO getTrajetDAO() {
        try {
            return DaoManager.createDao(source, Trajet.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

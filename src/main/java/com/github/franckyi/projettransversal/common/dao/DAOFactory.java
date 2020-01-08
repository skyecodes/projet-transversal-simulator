package com.github.franckyi.projettransversal.common.dao;

import com.github.franckyi.projettransversal.common.ConnectionHandler;

import java.sql.Connection;

public class DAOFactory {

    private static final Connection connection = ConnectionHandler.getConnection();

    private static CamionDAO camionDAO;
    private static CaserneDAO caserneDAO;
    private static FeuDAO feuDAO;
    private static PointDAO pointDAO;

    public static CamionDAO getCamionDAO() {
        if (camionDAO == null) {
            camionDAO = new CamionDAO(connection);
        }
        return camionDAO;
    }

    public static CaserneDAO getCaserneDAO() {
        if (caserneDAO == null) {
            caserneDAO = new CaserneDAO(connection);
        }
        return caserneDAO;
    }

    public static FeuDAO getFeuDAO() {
        if (feuDAO == null) {
            feuDAO = new FeuDAO(connection);
        }
        return feuDAO;
    }

    public static PointDAO getPointDAO() {
        if (pointDAO == null) {
            pointDAO = new PointDAO(connection);
        }
        return pointDAO;
    }

}

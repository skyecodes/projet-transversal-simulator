package com.github.franckyi.projettransversal.util;

import com.github.franckyi.projettransversal.api.APIHandler;
import com.github.franckyi.projettransversal.api.Directions;
import com.github.franckyi.projettransversal.common.dao.DAOFactory;
import com.github.franckyi.projettransversal.common.model.Caserne;
import com.github.franckyi.projettransversal.common.model.Point;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws SQLException {
        Caserne c = DAOFactory.getCaserneDAO().queryForId(1);
        Point p = DAOFactory.getPointDAO().queryForId(1);
        Directions d = APIHandler.getDirections(c, p);
        System.out.println(d);
    }

}

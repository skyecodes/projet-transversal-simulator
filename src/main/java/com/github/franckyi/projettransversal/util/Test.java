package com.github.franckyi.projettransversal.util;

import com.github.franckyi.projettransversal.api.APIHandler;
import com.github.franckyi.projettransversal.common.ConnectionHandler;
import com.github.franckyi.projettransversal.common.dao.DAOFactory;
import com.github.franckyi.projettransversal.common.model.Caserne;
import com.github.franckyi.projettransversal.common.model.Point;
import com.github.franckyi.projettransversal.common.model.Pos;

import java.sql.SQLException;
import java.util.List;

public class Test {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionHandler.init(ConnectionHandler.Database.REEL);
        Caserne c = DAOFactory.getCaserneDAO().queryForId(1);
        Point p = DAOFactory.getPointDAO().queryForId(1);
        List<Pos> d = APIHandler.getDirections(c, p);
        System.out.println(d);
    }

}

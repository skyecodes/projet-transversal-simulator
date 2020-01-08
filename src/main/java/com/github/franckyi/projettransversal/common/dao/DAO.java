package com.github.franckyi.projettransversal.common.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAO<T> {

    protected Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }

    public final boolean create(T obj) {
        try {
            return this.doCreate(obj);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean delete(T obj) {
        try {
            return this.doDelete(obj);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean update(T obj) {
        try {
            return this.doUpdate(obj);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final T find(int id) {
        try {
            return this.doFind(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected abstract boolean doCreate(T obj) throws SQLException;

    protected abstract boolean doDelete(T obj) throws SQLException;

    protected abstract boolean doUpdate(T obj) throws SQLException;

    protected abstract T doFind(int id) throws SQLException;

}

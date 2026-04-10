package ua.kpi.model.dao.jdbc;

import ua.kpi.model.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDaoConnection implements DaoConnection {
    private Connection connection;

    public PostgresDaoConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = DbProperties.get("db.url");
            String user = DbProperties.get("db.user");
            String password = DbProperties.get("db.password");

            System.out.println("DB URL = " + url);
            System.out.println("DB USER = " + user);

            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);

            System.out.println("DB connection OK");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void begin() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
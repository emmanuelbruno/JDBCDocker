package fr.univtln.bruno.i211.dao;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource {

    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl(System.getProperty("db.url"));
        ds.setUsername(System.getProperty("db.user"));
        ds.setPassword(System.getProperty("db.password"));
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    private DBCPDataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
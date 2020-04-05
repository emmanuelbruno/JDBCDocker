package fr.univtln.bruno.i211.dao.utils;

import lombok.extern.java.Log;
import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * The type Dbcp data source.
 */
//https://git-wip-us.apache.org/repos/asf?p=commons-dbcp.git;a=blob;f=doc/PoolingDataSourceExample.java;h=2a12c74898930b9623223db1597b8a8052a6f1df;hb=HEAD
@Log
public class DBCPDataSource {

    private static GenericObjectPool<PoolableConnection> connectionPool;
    private static PoolingDataSource poolingDataSource;
    private static Properties properties = new Properties();


    static {
        log.info("Init datasource");
        try (InputStream inputStream = DBCPDataSource.class.getResourceAsStream("/db.properties")) {
            properties.load(inputStream);
            log.info("Using database: " + properties.getProperty("db.url", "localhost/db"));

        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        ConnectionFactory connectionFactory =
                new DriverManagerConnectionFactory(properties.getProperty("db.url"),
                        properties.getProperty("db.username"),
                        properties.getProperty("db.password"));
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        poolableConnectionFactory.setValidationQuery("SELECT 1");

        //GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        //config.setMaxTotal(10);
        connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        poolingDataSource = new PoolingDataSource<>(connectionPool);
    }

    /**
     * Gets pooling data source.
     *
     * @return the pooling data source
     */
    public static PoolingDataSource getPoolingDataSource() {
        return poolingDataSource;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     * @throws SQLException the sql exception
     */
    public static Connection getConnection() throws SQLException {
        return poolingDataSource.getConnection();
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public static String getStatus() {
        return "Max   : " + connectionPool.getNumActive() + "; " +
                "Active: " + connectionPool.getNumActive() + "; " +
                "Idle  : " + connectionPool.getNumIdle();
    }

}
package com.verenitymc.xutil.backend.sql;

import com.verenitymc.xutil.Core;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Created by Mat
 *
 * Acts as a database wrapper for SQL.
 *
 * Create a new instance of this for a singular connection pool.
 */
public class SQLWrapper {

    private String user;
    private String database;
    private String password;
    private String port;
    private String hostname;

    private HikariDataSource dataSource;

    public SQLWrapper(String hostname, String port, String database, String username, String password)
    {
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;

        // Initialize hikari data source, used for connection pooling.

        dataSource = new HikariDataSource();

        getSource().setMaximumPoolSize(10);
        getSource().setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        getSource().addDataSourceProperty("serverName", hostname);
        getSource().addDataSourceProperty("port", port);
        getSource().addDataSourceProperty("databaseName", database);
        getSource().addDataSourceProperty("user", username);
        getSource().addDataSourceProperty("password", password);

        Core.log("Connection pool loaded!");

    }


    public HikariDataSource getSource()
    {
        return this.dataSource;
    }


}

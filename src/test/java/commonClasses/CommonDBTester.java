package commonClasses;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.fnb.automation.commonlib;


import java.sql.*;

/**
 * Created by f3307530 on 2015/02/16.
 */
public class CommonDBTester {

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    Connection connection = null;

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt){
        this.stmt = stmt;
    }

    Statement stmt = null;

    public CommonDBTester(){};

    public CommonDBTester(String jdbcUrl, String username, String password, String driverName)throws SQLException,ClassNotFoundException{
            Class.forName(driverName);
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            stmt = connection.createStatement();
    }

    public ResultSet getResultSet(String query){
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void closeConnection(){

        try{

        stmt.close();
        connection.close();

    }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

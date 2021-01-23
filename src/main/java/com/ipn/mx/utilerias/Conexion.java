/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.utilerias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author werog
 */
public class Conexion {
//     public static Connection obtenerConexion() {
//        String usr = "postgres";
//        String pwd = "root";
//        String driver = "org.postgresql.Driver";
//        //String driver = "com.mysql.cj.jdbc.Driver";
//        //String url = "jdbc:mysql://localhost:3306"; //mysql 5.7 con driver
//        String url = "jdbc:postgresql://localhost:5432/3cm4";
//        Connection connection=null;
//        try {
//            Class.forName(driver);
//            connection = DriverManager.getConnection(url, usr, pwd);
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return connection;
//    }
     
     public static Connection obtenerConexion() {
        String usr = "wpanvxduexxvcy";
        String pwd = "787bd87daf8a5eea6c3098b5cfd175e30a41a678341bcf4fb17b227e8df8ef8b";
        String driver = "org.postgresql.Driver";
        //String driver = "com.mysql.cj.jdbc.Driver";
        //String url = "jdbc:mysql://localhost:3306"; //mysql 5.7 con driver
        String url = "jdbc:postgresql://ec2-54-158-222-248.compute-1.amazonaws.com:5432/dd7c18egm8fkp3?sslmod=required";
        Connection connection=null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, usr, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
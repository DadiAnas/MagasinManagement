package com.shop;
// pattern singleton

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static Connexion connexionSingle=null;
    private Connection connection;

    private Connexion(){
        try {
            Class.forName("com.mysql.jdbc.Connection");
            String url = "jdbc:mysql://localhost:3306/salesmanagment" ;
            connection = DriverManager.getConnection(url,"root","");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return this.connection;
    }
    public static Connexion getConnexion(){
        if(connexionSingle==null){
            connexionSingle = new Connexion();
        }
        return connexionSingle;
    }
}

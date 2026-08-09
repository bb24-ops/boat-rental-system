/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author boris
 */
public class DbConnectionFactory {
    private static DbConnectionFactory instanca;
    private Connection connection;

    public static DbConnectionFactory getInstance() {
        if (instanca == null) {
            instanca = new DbConnectionFactory();
        }
        return instanca;
    }

    private DbConnectionFactory() {

        try {
            if (connection == null || connection.isClosed()) {
                String url = konfiguracija.Konfiguracija.getInstanca().getProperty("url");
                String username = konfiguracija.Konfiguracija.getInstanca().getProperty("username");
                String password = konfiguracija.Konfiguracija.getInstanca().getProperty("password");
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    

}

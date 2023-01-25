/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utem.groupproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author mangy
 */
public class Database {
    
    private Connection conn;
    
    public Connection openConnection() throws ClassNotFoundException{
        if (conn == null){
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "store";
            String driver = "com.mysql.cj.jdbc.Driver";
            String username = "root";
            String password = "";
            
            try{
                Class.forName(driver);
                conn = (Connection)DriverManager.getConnection(url+dbName,username,password);
            }
            catch(SQLException err){
                JOptionPane.showMessageDialog(null,err.getMessage());
            }
        }
        return conn;
    }
    
    public Connection closeConnection() throws SQLException{
        conn.close();
        JOptionPane.showMessageDialog(null,"Connection Closed");
        return conn;
    }
}

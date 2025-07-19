/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.database;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Roger
 */
public class Database {
    private Connection connection;
    
    public Database() {
        this.connection = null;
    }
    
    public void connect(String host, Integer port, String database, String username, String password) {
        try {
            this.connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port.toString() + "/" + database, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } 
    }
    
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            this.connection = null;
        }
    }
    
    public void doSendingQuery(String query) {
        try {
            this.connection.prepareStatement(query).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    public String[][] doReceivingQuery(String query, int columnAmount) {
        try {
            ResultSet rs = this.connection.prepareStatement(query).executeQuery();
            
            ArrayList<String[]> results = new ArrayList<>();
            ArrayList<String> current;
            
            while (rs.next()) {
                current = new ArrayList<>();
                for (int i = 1; i <= columnAmount; i++) {
                    current.add(rs.getString(i));
                }
                results.add(current.toArray(String[]::new));
                current = null;
            }
            return results.toArray(String[][]::new);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }    
    
    
}   



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package src;

/**
 *
 * @author Roger
 */
import java.sql.SQLException;
import org.postgresql.*;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Driver d = new Driver();
        d.connect("localhost", null);

        System.out.println("Hello, world!");
    }
    
}

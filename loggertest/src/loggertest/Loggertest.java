/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loggertest;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author Roger
 */
public class Loggertest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("minecraft");
        } catch (FileNotFoundException ex) {
            System.getLogger(Loggertest.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}

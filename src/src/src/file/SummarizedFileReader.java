/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Roger
 */
public class SummarizedFileReader {
    private static String readFile(String fileName) {
        String file;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = null;
            file = "";
            
            while ((line = br.readLine()) != null) {
                file = file + (line + '\n');
            }
        } catch (IOException ex) {
            file = null;
        }
        return file;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validations;

import java.lang.System.Logger;

/**
 *
 * @author Roger
 */
public class PrintableException extends Exception {
    public static boolean DEBUG_MESSAGES = true;
    
    public PrintableException() {
        super("Error desconocido.");
    }

    public PrintableException(String message) {
        super(message);
    }

    public PrintableException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrintableException(Throwable cause) {
        super(cause);
    }

    public PrintableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    public static void PrintOtherException(Exception e) {
        if (DEBUG_MESSAGES) {
            System.getLogger(PrintableException.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
            /*
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            for (Object o : e.getStackTrace()) {
                try {
                    System.out.println(o.toString());
                } catch (Exception ex) {}
            }
            */
        }
    }
}

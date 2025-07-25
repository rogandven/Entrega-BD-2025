/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validations;

/**
 *
 * @author Roger
 */
public class PrintableException extends Exception {
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
}
